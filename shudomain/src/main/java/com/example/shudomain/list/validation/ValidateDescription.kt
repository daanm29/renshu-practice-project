package com.example.shudomain.list.validation

import com.example.shudomain.list.exception.InvalidDescriptionException
import javax.inject.Inject

class ValidateDescription @Inject constructor() {

    operator fun invoke(description: String): Throwable? {
        return if (description.isBlank()) {
            InvalidDescriptionException()
        } else {
            null
        }
    }
}
