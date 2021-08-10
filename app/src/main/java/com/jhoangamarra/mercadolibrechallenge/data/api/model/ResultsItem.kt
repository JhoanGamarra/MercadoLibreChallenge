package com.jhoangamarra.mercadolibrechallenge.data.api.model

import com.jhoangamarra.mercadolibrechallenge.core.formatPrice
import com.jhoangamarra.mercadolibrechallenge.domain.model.ProductDomainModel
import com.squareup.moshi.Json

data class ResultsItem(

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "price")
    val price: Float? = null,

    @Json(name = "currency_id")
    val currency_id: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "thumbnail")
    val thumbnail: String? = null,

    @Json(name = "condition")
    val condition: String,

    @Json(name = "available_quantity")
    val available_quantity: Int,

    @Json(name = "accepts_mercadopago")
    val accepts_mercadopago: Boolean,

    )

fun ResultsItem.toDomainModel(): ProductDomainModel {

    return ProductDomainModel(
        productImage = this.thumbnail ?: "http://maestroselectronics.com/wp-content/uploads/2017/12/No_Image_Available.jpg",
        title = this.title ?: "Titulo Vacio",
        id = this.id ?: "Id Vacio",
        price = formatPrice(price = this.price, currencyId = this.currency_id),
        condition = this.condition ?: "Sin informacion",
        availableQuantity = this.available_quantity,
        availableMercadoPago = this.accepts_mercadopago
    )
}