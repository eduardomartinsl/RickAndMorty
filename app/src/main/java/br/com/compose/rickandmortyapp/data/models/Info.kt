package br.com.compose.rickandmortyapp.data.models

import com.squareup.moshi.Json

data class Info(
    @Json(name = "count")
    val count: Int,

    @Json(name = "pages")
    val pages: Int,

    @Json(name = "next")
    val nextPage: String?,

    @Json(name = "prev")
    val previousPage: String?
)