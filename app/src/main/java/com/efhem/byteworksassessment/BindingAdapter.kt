package com.efhem.byteworksassessment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.efhem.byteworksassessment.domain.model.Employee
import com.efhem.byteworksassessment.ui.ListEmployeeAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    errorMessage?.let {
      view.error = errorMessage
    }
}

@BindingAdapter("imageUrl", "cropping")
fun setImage(view: ImageView, url: String?, cropping: String? = "centerCrop") {
    val glide = Glide.with(view.context).load(url)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_image_placeholder))
        .apply(RequestOptions.errorOf(R.drawable.ic_image_placeholder))

    when (cropping) {
        "centerCrop" -> glide.apply(RequestOptions.centerCropTransform())
    }

    glide.diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view)
}

@BindingAdapter("listEmployee")
fun bindGallery(recyclerView: RecyclerView, data: List<Employee>?) {
    val adapter = recyclerView.adapter as ListEmployeeAdapter
    adapter.submitList(data)
}