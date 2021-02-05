package com.efhem.byteworksassessment.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.FragmentMainBinding
import com.efhem.byteworksassessment.domain.model.AppBar
import com.efhem.byteworksassessment.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment : Fragment() {

    private  var _bind: FragmentMainBinding? = null
    private val bind: FragmentMainBinding get() = _bind!!
    private lateinit var navController: NavController

    private var appBarState = AppBar.STANDARD_APPBAR

    private val viewModel: MainViewModel by viewModel()

    private var adapter: ListEmployeeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentMainBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        bind.viewmodel = viewModel
        bind.lifecycleOwner = this

        setAppBaeState(AppBar.STANDARD_APPBAR)

        navigateToDetails()


        adapter = setupRecyclerview(bind.rcEmployee)
        bind.rcEmployee.adapter = adapter

        //observeEmployeeData()

        bind.searchAppbar.etSearchContacts.doAfterTextChanged {
            viewModel.setSearchWord(it.toString())
        }

        bind.searchAppbar.ivBackArrow.setOnClickListener { toggleToolBarState() }
        bind.mainAppbar.ivSearchIcon.setOnClickListener { toggleToolBarState() }

        bind.fabForm.setOnClickListener { navController.navigate(MainFragmentDirections.actionMainFragmentToAddEmployeeFragment()) }

        return bind.root
    }

    private fun navigateToDetails(){
        viewModel.navigateToEmployeeDetails.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { email ->
                navController.navigate(
                    MainFragmentDirections.actionMainFragmentToEmployeeDetailsFragment(email)
                )
            }
        })
    }

    private fun setupRecyclerview(rc: RecyclerView): ListEmployeeAdapter {
        val verticalDecorator =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val horizontalDecorator =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        val drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.divider, resources.newTheme())
        if (drawable != null) {
            verticalDecorator.setDrawable(drawable)
            horizontalDecorator.setDrawable(drawable)
        }
        rc.addItemDecoration(verticalDecorator)
        rc.addItemDecoration(horizontalDecorator)
        return ListEmployeeAdapter(viewModel)
    }

//    private fun observeEmployeeData(){
//        viewModel.employeeObservable.observe(viewLifecycleOwner) {
//            println("value is ${it?.size}")
//            adapter?.submitList(it)
//        }
//    }

    // Sets the appbar state for either search mode or standard mode.
    private fun setAppBaeState(state: AppBar) {
        appBarState = state
        if (appBarState === AppBar.STANDARD_APPBAR) {
            bind.searchAppbar.searchToolbar.visibility = View.GONE
            bind.mainAppbar.viewMainToolbar.visibility = View.VISIBLE
            val view = view
            val im: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            try {
                im.hideSoftInputFromWindow(view!!.windowToken, 0) // make keyboard hide
            } catch (e: NullPointerException) {
                Timber.d("setAppBaeState: NullPointerException: $e")
            }
        } else if (appBarState === AppBar.SEARCH_APPBAR) {
            bind.mainAppbar.viewMainToolbar.visibility = View.GONE
            bind.searchAppbar.searchToolbar.visibility = View.VISIBLE
            val im: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0) // make keyboard popup
        }
    }

    private fun toggleToolBarState() {
        if (appBarState === AppBar.STANDARD_APPBAR) {
            setAppBaeState(AppBar.SEARCH_APPBAR)
        } else {
            setAppBaeState(AppBar.STANDARD_APPBAR)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    override fun onResume() {
        super.onResume()
        setAppBaeState(AppBar.STANDARD_APPBAR)
    }
}