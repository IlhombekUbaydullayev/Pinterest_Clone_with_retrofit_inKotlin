package com.example.pinterest_clone_with_retrofit_inkotlin.activity.model

typealias Topics = ArrayList<Topic>

data class Topic (
    val id: String? = null,
    val slug: String? = null,
    val title: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val updatedAt: String? = null,
    val startsAt: String? = null,
    val endsAt: String? = null,
    val onlySubmissionsAfter: Any? = null,
    val featured: Boolean? = null,
    val totalPhotos: Long? = null,
    val currentUserContributions: List<Any?>? = null,
    val totalCurrentUserSubmissions: Any? = null,
    val links: TopicLinks? = null,
    val status: String? = null,
    val owners: List<User>? = null,
    val coverPhoto: Photo? = null,
    val previewPhotos: List<PreviewPhoto>? = null
)


data class CoverPhotoLinks (
    val self: String? = null,
    val html: String? = null,
    val download: String? = null,
    val downloadLocation: String? = null
)

data class TopicSubmissions (
    val actForNature: The3_DRenders? = null,
    val nature: The3_DRenders? = null,
    val entrepreneur: The3_DRenders? = null,
    val artsCulture: ArtsCulture? = null,
    val businessWork: The3_DRenders? = null,
    val currentEvents: The3_DRenders? = null,
    val spirituality: ArtsCulture? = null,
    val travel: ArtsCulture? = null,
    val texturesPatterns: The3_DRenders? = null,
    val wallpapers: The3_DRenders? = null,
    val experimental: ArtsCulture? = null,
    val the3DRenders: The3_DRenders? = null
)

data class The3_DRenders (
    val status: Status? = null,
    val approvedOn: String? = null
)

enum class Status {
    Approved,
    Rejected
}


data class TopicLinks (
    val self: String? = null,
    val html: String? = null,
    val photos: String? = null
)

data class PreviewPhoto (
    val id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val blurHash: String? = null,
    val urls: Urls? = null
)