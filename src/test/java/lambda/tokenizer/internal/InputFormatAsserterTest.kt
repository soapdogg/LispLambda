package lambda.tokenizer.internal

import lambda.core.constants.TokenValueConstants
import lambda.core.exceptions.InvalidTokenException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InputFormatAsserterTest {
    private val lineFormatAsserter = InputFormatAsserter(
        TokenValueConstants.ERROR_STATE_PATTERN
    )

    @Test
    fun lineAsserterDoesNotThrowTest() {
        Assertions.assertDoesNotThrow { lineFormatAsserter.assertInputFormat("") }
        Assertions.assertDoesNotThrow { lineFormatAsserter.assertInputFormat("2132132") }
        Assertions.assertDoesNotThrow { lineFormatAsserter.assertInputFormat("\tASW 34324 AS() ") }
    }

    @Test
    fun lineAsserterDoesThrowTest() {
        Assertions.assertThrows(
            InvalidTokenException::class.java
        ) { lineFormatAsserter.assertInputFormat("23432DFD") }
        Assertions.assertThrows(
            InvalidTokenException::class.java
        ) { lineFormatAsserter.assertInputFormat("2132132 343SDF") }
        Assertions.assertThrows(
            InvalidTokenException::class.java
        ) { lineFormatAsserter.assertInputFormat("\tASW 34324P AS() ") }
    }
}