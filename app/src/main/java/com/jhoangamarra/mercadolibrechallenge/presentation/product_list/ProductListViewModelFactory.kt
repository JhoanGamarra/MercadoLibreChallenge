package com.jhoangamarra.mercadolibrechallenge.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhoangamarra.mercadolibrechallenge.domain.use_cases.product_list.GetProductsUC

class ProductListViewModelFactory(private val getProductsUC: GetProductsUC) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetProductsUC::class.java).newInstance(getProductsUC)
    }
}