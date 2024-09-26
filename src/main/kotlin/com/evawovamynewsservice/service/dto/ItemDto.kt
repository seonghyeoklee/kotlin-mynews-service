package com.evawovamynewsservice.service.dto

import com.evawovamynewsservice.domain.News
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemDto(
    val title: String,
    val originallink: String,
    val link: String,
    val description: String,
    val pubDate: String,
) {
    fun toEntity() =
        News(
            title = title,
            link = link,
            originalLink = originallink,
            description = description,
            pubDate = pubDate,
        )
}
