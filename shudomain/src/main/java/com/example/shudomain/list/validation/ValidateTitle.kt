package com.example.shudomain.list.validation

import com.example.shudomain.list.exception.InvalidTitleException
import javax.inject.Inject

class ValidateTitle @Inject constructor() {

    operator fun invoke(title: String): Throwable? {
        return if (title.isBlank()) {
            InvalidTitleException()
        } else {
            null
        }
    }
}
