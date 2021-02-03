package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentEmployeeDetailsBinding
import com.efhem.byteworksassessment.databinding.FragmentFormBinding


class EmployeeDetailsFragment : Fragment() {


    private var _bind: FragmentEmployeeDetailsBinding? = null
    private val bind: FragmentEmployeeDetailsBinding get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentEmployeeDetailsBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}