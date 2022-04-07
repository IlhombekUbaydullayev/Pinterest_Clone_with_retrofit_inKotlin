package com.example.pinterest_clone_with_retrofit_inkotlin.activity.model

data class RelatedPhotos(
    var total: Int? = null,
    var results: ArrayList<Photo>? = null
)