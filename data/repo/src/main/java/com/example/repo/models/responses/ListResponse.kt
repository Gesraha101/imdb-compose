package com.example.repo.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListResponse<T>(val results: List<T>)