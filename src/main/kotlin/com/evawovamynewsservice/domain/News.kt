package com.evawovamynewsservice.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.Comment

@Entity
class News(
    title: String,
    link: String,
    originalLink: String,
    description: String,
    pubDate: String,
) : BaseEntity() {
    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("제목")
    @Column(nullable = false)
    var title: String = title
        protected set

    @Comment("링크")
    @Column(nullable = false)
    var link: String = link
        protected set

    @Comment("원본 링크")
    @Column(nullable = false)
    var originalLink: String = originalLink
        protected set

    @Comment("설명")
    @Column(nullable = false)
    var description: String = description
        protected set

    @Comment("발행일")
    @Column(nullable = false)
    var pubDate: String = pubDate
        protected set
}
