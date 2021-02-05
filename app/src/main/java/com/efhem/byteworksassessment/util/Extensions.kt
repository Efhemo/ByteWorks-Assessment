package com.efhem.byteworksassessment.util

import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText

inline fun <T, K, R> LiveData<T>.combineWith(
        liveData: LiveData<K>,
        crossinline block: (T?, K?) -> R
): MutableLiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}

fun AppCompatAutoCompleteTextView.disableKeyBoard(){
    //restrict keyboard from showing up
    requestFocus()
    showSoftInputOnFocus = false
    threshold = 2
}

fun TextInputEditText.disableKeyBoard(){
    //restrict keyboard from showing up
    requestFocus()
    showSoftInputOnFocus = false
}