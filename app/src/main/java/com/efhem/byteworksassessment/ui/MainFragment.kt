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
import com.efhem.byteworksassessment.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private  var _bind: FragmentMainBinding? = null
    private val bind: FragmentMainBinding get() = _bind!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentMainBinding.inflate(inflater, container, false)

        navController = NavHostFragment.findNavController(this)
        bind.fabForm.setOnClickListener { navController.navigate(MainFragmentDirections.actionMainFragmentToAddEmployeeFragment()) }

        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}