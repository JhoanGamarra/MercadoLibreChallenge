package com.jhoangamarra.mercadolibrechallenge.core

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.text.NumberFormat
import java.util.*

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}


fun formatPrice(price: Float?, currencyId: String?): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    currencyId?.let {
        format.currency = Currency.getInstance(currencyId)
    }
    return format.format(price ?: 0)
}