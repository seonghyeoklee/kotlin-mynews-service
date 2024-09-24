package com.evawovamynewsservice.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class News(
    title: String,
    link: String,
    searchKeyword: String,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var link: String = link
        protected set

    @Column(nullable = false)
    var searchKeyword: String = searchKeyword
        protected set
}
