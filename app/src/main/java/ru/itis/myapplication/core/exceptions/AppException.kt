package ru.itis.myapplication.core.exceptions

import ru.itis.myapplication.core.constants.ErrorConstants

sealed class AppException(message: String) : Exception(message) {
    class Unauthorized : AppException(ErrorConstants.UNAUTHORIZED)
    class NotFound : AppException(ErrorConstants.NOT_FOUND)
    class ServerError : AppException(ErrorConstants.SERVER_ERROR)
    class Unknown : AppException(ErrorConstants.UNKNOWN_ERROR)
    class UnknownHost : AppException(ErrorConstants.NO_CONNECTION)
}