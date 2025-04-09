package com.heba.task_app.core.common


sealed class Resource<out Model> {
    data class Success<Model>(val model: Model) : Resource<Model>()
    data class Failure(val exception: String) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
}