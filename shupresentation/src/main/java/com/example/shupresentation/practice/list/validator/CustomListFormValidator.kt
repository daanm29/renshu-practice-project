package com.example.shupresentation.practice.list.validator

import com.example.shudomain.list.validation.ValidateDescription
import com.example.shudomain.list.validation.ValidateTitle
import com.example.shupresentation.practice.list.validator.CustomListFormField.DESCRIPTION
import com.example.shupresentation.practice.list.validator.CustomListFormField.TITLE
import javax.inject.Inject

class CustomListFormValidator @Inject constructor(
    private val validateTitle: ValidateTitle,
    private val validateDescription: ValidateDescription,
) {

    private fun validateForm(formField: CustomListFormField, form: CustomListForm): Throwable? {
        return when (formField) {
            TITLE -> validateTitle(form.title)
            DESCRIPTION -> validateDescription(form.description)
        }
    }

    fun getInvalidFromFields(form: CustomListForm): List<CustomListFormField> {
        return CustomListFormField.values().filterNot { validateForm(it, form) == null }
    }
}
