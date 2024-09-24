package com.evawovamynewsservice.controller.dto

import com.evawovamynewsservice.service.dto.NewsDto
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResponseNews(
    @Schema(description = "뉴스 ID")
    val id: Long,
    @Schema(description = "뉴스 제목")
    val title: String,
    @Schema(description = "뉴스 링크")
    val link: String,
    @Schema(description = "검색어")
    val searchKeyword: String,
    @Schema(description = "생성 일시")
    val createdAt: LocalDateTime,
    @Schema(description = "수정 일시")
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
