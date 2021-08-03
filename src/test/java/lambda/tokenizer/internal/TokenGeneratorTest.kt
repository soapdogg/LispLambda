package lambda.tokenizer.internal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TokenGeneratorTest {
    private val value = "value"
    private val tokenGenerator = TokenGenerator()

    @Test
    fun generateLiteralTokenTest() {
        val actual = tokenGenerator.generateLiteralToken(value)
        Assertions.assertEquals(value, actual.value)
    }
}