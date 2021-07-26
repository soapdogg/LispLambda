package lambda.tokenizer

import lambda.tokenizer.internal.InputFormatAsserter
import lambda.datamodels.Token
import lambda.tokenizer.internal.InputTokenizer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TokenizerTest {
    private val inputFormatAsserter: InputFormatAsserter = Mockito.mock(InputFormatAsserter::class.java)
    private val inputTokenizer: InputTokenizer = Mockito.mock(InputTokenizer::class.java)
    private val tokenizer: Tokenizer = Tokenizer(
        inputFormatAsserter,
        inputTokenizer
    )


    @Test
    fun tokenizerTest() {
        val line = "line"

        val token = Mockito.mock(Token::class.java)
        val tokens = listOf(token)
        Mockito.`when`(
            inputTokenizer.tokenizeInput(line)
        ).thenReturn(tokens)

        val actual = tokenizer.tokenize(line)

        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(token, actual[0])
        Mockito.verify(inputFormatAsserter).assertInputFormat(line)
    }
}