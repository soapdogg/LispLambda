package lambda.tokenizer.internal

import lambda.core.datamodels.TokenKind
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TokenGeneratorTest {
    private val value = "value"
    private val tokenGenerator = TokenGenerator()

    @Test
    fun generateLiteralTokenTest() {
        val (tokenKind, value1) = tokenGenerator.generateLiteralToken(value)
        Assertions.assertEquals(TokenKind.LITERAL_TOKEN, tokenKind)
        Assertions.assertEquals(value, value1)
    }
}