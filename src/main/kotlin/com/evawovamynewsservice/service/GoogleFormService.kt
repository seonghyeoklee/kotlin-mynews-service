package com.evawovamynewsservice.service

import com.evawovamynewsservice.controller.GoogleFormData
import com.evawovamynewsservice.controller.Response
import com.evawovamynewsservice.domain.Form
import com.evawovamynewsservice.domain.FormResult
import com.evawovamynewsservice.repository.FormRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GoogleFormService(
    private val formRepository: FormRepository,
) {
    @Transactional
    fun saveGoogleForm(formData: GoogleFormData) {
        val form =
            Form(
                formId = formData.formId,
                formTitle = formData.formTitle,
                email = formData.email,
            )

        formData.results.map {
            val formResult =
                FormResult(
                    resultId = it.id,
                    type = it.type,
                    title = it.title,
                    responseText =
                        when (it.response) {
                            is Response.TextResponse -> it.response.response
                            is Response.CheckboxResponse -> it.response.response.joinToString(",")
                            is Response.EmptyResponse -> ""
                        },
                )
            form.addResult(formResult)
        }
        formRepository.save(form)
    }
}
