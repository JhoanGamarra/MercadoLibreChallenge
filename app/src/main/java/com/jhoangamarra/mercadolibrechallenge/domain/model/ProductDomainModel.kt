package com.jhoangamarra.mercadolibrechallenge.domain.model

data class ProductDomainModel(
    val productImage: String,
    val title: String,
    val price: String,
    val condition : String,
    val availableQuantity : Int,
    val availableMercadoPago : Boolean,
    val id: String
)