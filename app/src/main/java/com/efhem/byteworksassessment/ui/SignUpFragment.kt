package com.efhem.byteworksassessment.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentSignUpBinding
import com.efhem.byteworksassessment.util.Utils
import com.efhem.byteworksassessment.util.disableKeyBoard
import com.efhem.byteworksassessment.viewmodels.SignUpViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private  var _bind: FragmentSignUpBinding? = null
    private val bind: FragmentSignUpBinding get() = _bind!!
    private lateinit var navController: NavController

    private val viewModel: SignUpViewModel by viewModel()

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            startCropImage(uri)
        }else Toast.makeText(requireContext(), getString(R.string.photo_not_selected), Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentSignUpBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        bind.viewmodel = viewModel
        bind.lifecycleOwner = this

        bind.photoContainer.setOnClickListener { selectImage() }
        bind.gender.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.male){
                viewModel.setGender("Male")
            } else viewModel.setGender("Female")
        }

        signUpAdmin()

        bind.appbar.title.text = getString(R.string.sign_up)
        bind.appbar.btnBackArrow.setOnClickListener { navController.popBackStack() }

        viewModel.message.observe(viewLifecycleOwner){
            it?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }

        seUpDOB()
        setUpCountryState()

        return bind.root
    }

    private fun seUpDOB() {
        bind.edDob.apply {
            this.disableKeyBoard()
            setOnClickListener {
                Utils.showDatePicker(childFragmentManager) {
                    bind.edlDob.editText?.setText(it)
                }
            }
        }
    }

    private fun setUpCountryState() {
        bind.edCountry.apply {
            disableKeyBoard()
            viewModel.observeCountry.observe(viewLifecycleOwner, Observer {
                val stateString = it.map { it.name }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    stateString
                )
                setAdapter(adapter)
                setOnItemClickListener { parent, _, position, _ ->
                    bind.edState.setText("")
                    val selectedCountry = bind.edCountry.text.toString()
                    val correspondedState = it.find { it.name == selectedCountry }?.states
                    viewModel.setState(correspondedState)
                }
            })
        }

        bind.edState.apply {
            disableKeyBoard()
            viewModel.observeState.observe(viewLifecycleOwner, Observer {
                val stateString = it?.map { it.name }
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    if (stateString.isNullOrEmpty()) emptyList() else stateString
                )
                setAdapter(adapter)
            })
        }
    }

    private fun signUpAdmin(){
        viewModel.navigateToSignInPage.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { toNavigate ->
                if(toNavigate){
                    navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                }
            }
        })
    }

    private fun selectImage() {
        getContent.launch("image/*")
    }

    private fun startCropImage(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1920, 2480)
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .start(requireContext(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = CropImage.getActivityResult(data)
            viewModel.setPhoto(result.uri.toString())
            Glide.with(requireContext()).load(result.uri).apply(RequestOptions.fitCenterTransform()).into(bind.imgPhoto)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}