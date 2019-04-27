package com.digian.example.moshireflection.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Alex Forrester on 11/04/2019.
 */
data class Movie (
    @Json(name = "vote_count") val voteCount: Int,
    val id: Int,
    val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    val title: String,
    val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "genre_ids") val genres: List<Genre>,
    @Json(name = "backdrop_path") val backdropPath: String?,
    val overview: String,
    @Json(name = "release_date") val releaseDate: String
)
