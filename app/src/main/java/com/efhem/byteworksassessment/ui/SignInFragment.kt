package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.efhem.byteworksassessment.databinding.FragmentSignInBinding
import com.efhem.byteworksassessment.viewmodels.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInFragment : Fragment() {

    private  var _bind: FragmentSignInBinding? = null
    private  val bind: FragmentSignInBinding  get() = _bind!!
    private lateinit var navController: NavController

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentSignInBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        bind.viewmodel = viewModel
        bind.lifecycleOwner = this

        signInUser()
        navigateToSignUp()

        return bind.root
    }

    private fun signInUser(){
        viewModel.navigateToMainPage.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { toMainPage ->
                if(toMainPage){
                    navController.navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
                }
            }
        })
    }

    private fun navigateToSignUp(){
        viewModel.navigateToSignUp.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                navController.navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}