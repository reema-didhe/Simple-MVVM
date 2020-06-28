package org.app.mdta.Interface

interface CallbackResponse {
    fun <T> onSuccess(response: T)
    fun <T> onFailure(response: T)
}