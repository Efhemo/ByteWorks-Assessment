package com.efhem.byteworksassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentFormBinding

class AddEmployeeFragment : Fragment() {

    private var _bind: FragmentFormBinding? = null
    private val bind: FragmentFormBinding = _bind!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentFormBinding.inflate(inflater, container, false)

        return bind.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

}