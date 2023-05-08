package com.example.acronymdictionaryapp.feature_acronym.data.util

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterizedValidationUtilTest(
    private val input: String,
    private val expectedValue: Boolean
) {

    @Test
    fun test() {
        val result = ValidationUtil.isValid(input).first
        assertEquals(expectedValue, result)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is valid - {1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("", false),
                arrayOf("s", false),
                arrayOf("s6*", false),
                arrayOf("sf", true)
            )
        }
    }
}