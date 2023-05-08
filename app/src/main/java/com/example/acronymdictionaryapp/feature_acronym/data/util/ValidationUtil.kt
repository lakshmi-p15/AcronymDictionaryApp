package com.example.acronymdictionaryapp.feature_acronym.data.util

object ValidationUtil {
    private const val SUCCESS_MESSAGE = "Success"
    private const val EMPTY_ACRONYM_MESSAGE = "Please enter valid acronym"
    private const val NON_ALPHABETIC_ACRONYM_MESSAGE = "Acronym can only contain alphabets"
    private const val SINGLE_CHARACTER_ACRONYM_MESSAGE = "Acronym can't be a single character"
    const val NETWORK_ERROR_MESSAGE = "Please connect to a network"
    const val RESPONSE_ERROR_MESSAGE = "No acronym definition found"

    fun isValid(acronym: String): Pair<Boolean, String>{
        return if (acronym.isEmpty())
            Pair(false, EMPTY_ACRONYM_MESSAGE)
        else if (acronym.length == 1)
            Pair(false, SINGLE_CHARACTER_ACRONYM_MESSAGE)
        else if ( !(acronym.matches("^[a-zA-Z]*$".toRegex())))
            Pair(false, NON_ALPHABETIC_ACRONYM_MESSAGE)
        else
            Pair(true, SUCCESS_MESSAGE)
    }
}