package lambda.tokenizer

import lambda.asserter.LineFormatAsserter
import lambda.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class TokenizerTest {
    private val lineFormatAsserter: LineFormatAsserter = Mockito.mock(LineFormatAsserter::class.java)
    private val lineTokenizer: LineTokenizer = Mockito.mock(LineTokenizer::class.java)
    private val tokenizer: Tokenizer = Tokenizer(
        lineFormatAsserter,
        lineTokenizer
    )


    @Test
    fun tokenizerTest() {
        val line = "line"
        val lines = listOf(line)

        val token = Mockito.mock(Token::class.java)
        val tokens = listOf(token)
        Mockito.`when`(
            lineTokenizer.tokenizeLine(line)
        ).thenReturn(tokens)

        val actual = tokenizer.tokenize(lines)

        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(token, actual[0])
        Mockito.verify(lineFormatAsserter).assertLineFormat(line)
    }
}