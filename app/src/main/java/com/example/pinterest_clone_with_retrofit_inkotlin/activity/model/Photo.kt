package com.example.pinterest_clone_with_retrofit_inkotlin.activity.model
import java.io.Serializable
typealias Photos = ArrayList<Photo>

data class Photo(
    val alt_description: Any? = null,
    val blur_hash: String? = null,
    val categories: List<Any>? = null,
    val color: String? = null,
    val created_at: String? = null,
    val current_user_collections: List<Any>? = null,
    val description: String? = null,
    val height: Int? = null,
    val id: String? = null,
    val liked_by_user: Boolean? = null,
    val likes: Int? = null,
    val links: PhotoLinks? = null,
    val promoted_at: String? = null,
    val sponsorship: Sponsorship? = null,
    val topic_submissions: TopicSubmissions? = null,
    val updated_at: String? = null,
    val urls: Urls? = null,
    val user: User? = null,
    val width: Int? = null,
): Serializable

data class PhotoLinks(
    val download: String? = null,
    val download_location: String? = null,
    val html: String? = null,
    val self: String? = null
): Serializable

data class Sponsorship(
    val impressionUrls: List<String>?,
    val tagline: String?,
    val taglineURL: String?,
    val sponsor: User?,
): Serializable

data class User(
    val accepted_tos: Boolean? = null,
    val bio: String? = null,
    val first_name: String? = null,
    val for_hire: Boolean? = null,
    val id: String? = null,
    val instagram_username: String? = null,
    val last_name: Any? = null,
    val links: UserLinks? = null,
    val location: String? = null,
    val name: String? = null,
    val portfolio_url: Any? = null,
    val profile_image: ProfileImage? = null,
    val social: Social? = null,
    val total_collections: Int? = null,
    val total_likes: Int? = null,
    val total_photos: Int? = null,
    val twitter_username: Any? = null,
    val updated_at: String? = null,
    val username: String? = null
): Serializable

data class UserLinks(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?,
    val portfolio: String?,
    val following: String?,
    val followers: String?,
): Serializable

data class ProfileImage(
    val small: String?,
    val medium: String?,
    val large: String?,
):Serializable

data class Social(
    val instagramUsername: String? = null,
    val portfolioURL: String? = null,
    val twitterUsername: String? = null,
    val paypalEmail: Any? = null,
): Serializable


data class ArtsCulture(
    val status: String?,
    val approvedOn: String?,
): Serializable

data class Urls(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?,
    val smallS3: String?,
): Serializable