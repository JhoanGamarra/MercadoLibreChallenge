package com.jhoangamarra.mercadolibrechallenge.domain.repository

import com.jhoangamarra.mercadolibrechallenge.core.Result
import com.jhoangamarra.mercadolibrechallenge.domain.model.ProductDomainModel

interface SearchRepository {

    suspend fun getProducts(
        siteId: String,
        query: String,
        position: Int
    ): Result<ProductDomainModel>


}