package com.example.criticaltechworkstaskapp.common


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }


        fun  error(msg: String): Resource<Nothing> {
            return Resource(Status.ERROR, null, msg)
        }


        fun  loading(): Resource<Nothing> {
            return Resource(Status.LOADING, null, null)
        }

    }

}

enum class  Status {LOADING, ERROR, SUCCESS}