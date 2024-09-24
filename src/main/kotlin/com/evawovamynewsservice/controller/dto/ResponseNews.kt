package com.evawovamynewsservice.controller.dto

import com.evawovamynewsservice.service.dto.NewsDto
import java.time.LocalDateTime

data class ResponseNews(
    val id: Long,
    val title: String,
    val link: String,
    val searchKeyword: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

fun NewsDto.toResponse() =
    ResponseNews(
        id = id,
        title = title,
        link = link,
        searchKeyword = searchKeyword,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
