package com.jhoangamarra.mercadolibrechallenge.core


sealed class Result<out T> {

    //class Loading<out T> : Result<T>()
    data class Success<T>(val data: List<T>?) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()

}