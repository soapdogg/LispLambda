package lambda.tokenizer.internal

import lambda.core.constants.TokenValueConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WordTokenizerTest {
    private val wordTokenizer= WordTokenizer()

    @Test
    fun closeParenthesesTest() {
        val word = TokenValueConstants.CLOSE_PARENTHESES
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0])
    }

    @Test
    fun openParenthesesTest() {
        val word = TokenValueConstants.OPEN_PARENTHESES
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0])
    }

    @Test
    fun literalTokenValueTest() {
        val word = "ABC"
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0])
    }
}