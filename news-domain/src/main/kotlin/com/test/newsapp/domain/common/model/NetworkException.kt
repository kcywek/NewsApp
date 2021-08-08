package com.test.newsapp.domain.common.model

sealed class NetworkException(message: String? = null) : Exception(message) {

    class Server(message: String? = null) : NetworkException(message)

    class Client(message: String? = null) : NetworkException(message)

    class Unexpected(message: String? = null) : NetworkException(message)

    class Unauthenticated : NetworkException()
}
