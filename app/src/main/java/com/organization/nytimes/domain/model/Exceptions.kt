package com.organization.nytimes.domain.model

import java.io.IOException


class NoMoreArticlesException(message: String): Exception(message)

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String): Exception(message)