package com.evawovamynewsservice.domain

import jakarta.persistence.*

@Entity
class Form(
    formId: String,
    formTitle: String,
    email: String,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false)
    var formId: String = formId
        protected set

    @Column(nullable = false)
    var formTitle: String = formTitle
        protected set

    @Column(nullable = false)
    var email: String = email
        protected set

    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var results: MutableList<FormResult> = mutableListOf()
        protected set

    fun addResult(result: FormResult) {
        results.add(result)
        result.form = this
    }
}
