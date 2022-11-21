package lambda.userdefined

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InvalidNameDeterminerTest {
    private val invalidName = "invalidName"
    private val invalidNames = setOf(invalidName)
    private val invalidNameDeterminer = InvalidNameDeterminerImpl(
        invalidNames
    )

    @Test
    fun invalidNamesContainsValueTest() {
        val value = invalidName
        val actual = invalidNameDeterminer.isInvalidName(value)
        Assertions.assertTrue(actual)
    }

    @Test
    fun valueIsNumericTest() {
        val value = "0"
        val actual = invalidNameDeterminer.isInvalidName(value)
        Assertions.assertTrue(actual)
    }

    @Test
    fun valueIsValidTest() {
        val value = "valid"
        val actual = invalidNameDeterminer.isInvalidName(value)
        Assertions.assertFalse(actual)
    }
}