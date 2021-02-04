package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentEmployeeDetailsBinding
import com.efhem.byteworksassessment.databinding.FragmentFormBinding
import com.efhem.byteworksassessment.viewmodels.MainViewModel
import com.efhem.byteworksassessment.viewmodels.SignInViewModel


class EmployeeDetailsFragment : Fragment() {


    private val args: EmployeeDetailsFragmentArgs by navArgs()
    private var _bind: FragmentEmployeeDetailsBinding? = null
    private val bind: FragmentEmployeeDetailsBinding get() = _bind!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentEmployeeDetailsBinding.inflate(inflater, container, false)

        bind.viewmodel = viewModel
        bind.lifecycleOwner = this

        val id  = args.employeeId

        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}