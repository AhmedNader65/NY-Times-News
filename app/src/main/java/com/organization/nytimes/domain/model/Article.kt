package com.organization.nytimes.domain.model

import com.organization.nytimes.data.api.ApiParameters.MEDIUM210_TYPE
import com.organization.nytimes.data.api.ApiParameters.MEDIUM440_TYPE
import com.organization.nytimes.data.api.ApiParameters.THUMBNAIL_TYPE

data class Article(
    val id: Long,
    val url: String,
    val published_date: String,
    val updated: String,
    val byline: String,
    val title: String,
    val abstract: String,
    val media: List<Image>,
) {
    fun getSmallestImage(): String? {
        media.find { it.format == THUMBNAIL_TYPE }?.let {
            return it.url
        }.run {
            media.find { it.format == MEDIUM210_TYPE }?.let {
                return it.url
            }.run {
                return media.find { it.format == MEDIUM440_TYPE }?.url
            }
        }
    }
}

data class Image(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int,
)