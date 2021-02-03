package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentFormBinding
import com.efhem.byteworksassessment.databinding.FragmentSignInBinding

class SignUpFragment : Fragment() {

    private  var _bind: FragmentFormBinding? = null
    private val bind: FragmentFormBinding get() = _bind!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentFormBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        bind.appbar.btnBackArrow.setOnClickListener { navController.popBackStack() }
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}