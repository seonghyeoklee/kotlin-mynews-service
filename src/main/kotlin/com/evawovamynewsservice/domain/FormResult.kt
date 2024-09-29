package com.evawovamynewsservice.domain

import jakarta.persistence.*

@Entity
class FormResult(
    resultId: Int,
    type: String,
    title: String,
    responseText: String?,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false)
    var resultId: Int = resultId
        protected set

    @Column(nullable = false)
    var type: String = type
        protected set

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var responseText: String? = responseText
        protected set

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    var form: Form? = null
}
