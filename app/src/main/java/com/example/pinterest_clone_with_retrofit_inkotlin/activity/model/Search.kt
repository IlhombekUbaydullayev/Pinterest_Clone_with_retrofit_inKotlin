package com.example.pinterest_clone_with_retrofit_inkotlin.activity.model

data class Search (
    val total: Long?,
    val totalPages: Long?,
    val results: ArrayList<Photo>?
)