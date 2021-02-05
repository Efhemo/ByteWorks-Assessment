package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.efhem.byteworksassessment.databinding.FragmentEmployeeDetailsBinding
import com.efhem.byteworksassessment.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class EmployeeDetailsFragment : Fragment() {


    private val args: EmployeeDetailsFragmentArgs by navArgs()
    private var _bind: FragmentEmployeeDetailsBinding? = null
    private val bind: FragmentEmployeeDetailsBinding get() = _bind!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentEmployeeDetailsBinding.inflate(inflater, container, false)

        bind.appbar.btnBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.setEmployee(args.employeeId)

        viewModel.observeEmployee.observe(viewLifecycleOwner){
            bind.employee = it
        }
        bind.lifecycleOwner = this

        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}