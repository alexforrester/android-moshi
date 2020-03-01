package com.digian.example.moshicodegen.data

import com.squareup.moshi.JsonClass

/**
 * Created by Alex Forrester on 2019-04-26.
 */
@JsonClass(generateAdapter = true)
data class Genre(val id: Int, val name: String)