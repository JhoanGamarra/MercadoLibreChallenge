package com.jhoangamarra.mercadolibrechallenge.domain.use_cases.product_list

import com.jhoangamarra.mercadolibrechallenge.core.Result
import com.jhoangamarra.mercadolibrechallenge.domain.model.ProductDomainModel
import com.jhoangamarra.mercadolibrechallenge.domain.repository.SearchRepository

class GetProductsUC(private val searchRepository: SearchRepository) {


    suspend fun invoke(query: String, siteId: String, position: Int): Result<ProductDomainModel> = getProducts(siteId, query, position)


    private suspend fun getProducts(
        site: String,
        query: String,
        position: Int
    ): Result<ProductDomainModel> {

        return searchRepository.getProducts(site, query, position)

    }

}