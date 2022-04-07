package com.example.pinterest_clone_with_retrofit_inkotlin.activity.model

typealias Collections = ArrayList<Collection>

data class Collection(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val lastCollectedAt: String? = null,
    val updatedAt: String? = null,
    val curated: Boolean? = null,
    val featured: Boolean? = null,
    val totalPhotos: Long? = null,
    val private: Boolean? = null,
    val shareKey: String? = null,
    val tags: List<Tag>? = null,
    val links: CollectionLinks? = null,
    val user: User? = null,
    val coverPhoto: Photo? = null,
    val previewPhotos: List<PreviewPhoto>? = null
)

data class Tag (
    val type: Type? = null,
    val title: String? = null,
    val source: Source? = null
)

data class Source (
    val ancestry: Ancestry? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val description: String? = null,
    val metaTitle: String? = null,
    val metaDescription: String? = null,
    val coverPhoto: Photo? = null
)

data class Ancestry (
    val type: Category? = null,
    val category: Category? = null,
    val subcategory: Category? = null
)

data class Category (
    val slug: String? = null,
    val prettySlug: String? = null
)

enum class Type {
    LandingPage,
    Search
}

data class CollectionLinks(
    val self: String,
    val html: String,
    val photos: String,
    val related: String,
)