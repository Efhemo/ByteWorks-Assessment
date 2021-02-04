package com.efhem.byteworksassessment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efhem.byteworksassessment.R
import com.efhem.byteworksassessment.databinding.EmployeeItemBinding
import com.efhem.byteworksassessment.domain.model.Employee
import com.efhem.byteworksassessment.viewmodels.MainViewModel

class ListEmployeeAdapter(val viewModel: MainViewModel) :
    ListAdapter<Employee, ListEmployeeAdapter.ItemViewHolder>(DiffCallBackUnitLog()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
                return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.employee_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val employee = getItem(position)
        holder.bind.employee = employee
        holder.bind.executePendingBindings()
        holder.bind.root.setOnClickListener {
            viewModel.viewEmployeeDetails(employee.id)
        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bind: EmployeeItemBinding = EmployeeItemBinding.bind(itemView)
    }

}

class DiffCallBackUnitLog : DiffUtil.ItemCallback<Employee>() {

    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id == newItem.id
    }
}