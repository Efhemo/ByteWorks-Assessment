package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentSignUpBinding
import com.efhem.byteworksassessment.viewmodels.SignUpViewModel

class SignUpFragment : Fragment() {

    private  var _bind: FragmentSignUpBinding? = null
    private val bind: FragmentSignUpBinding get() = _bind!!
    private lateinit var navController: NavController

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentSignUpBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        bind.viewmodel = viewModel
        bind.lifecycleOwner = this

        bind.gender.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.male){
                viewModel.setGender("Male")
            }else viewModel.setGender("Female")
        }

        signUpAdmin()

        bind.appbar.title.text = getString(R.string.sign_up)
        bind.appbar.btnBackArrow.setOnClickListener { navController.popBackStack() }

        return bind.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}