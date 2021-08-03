package lambda.tokenizer.internal

import lambda.core.constants.TokenValueConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class WordTokenizerTest {
    private val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer = Mockito.mock(
        LiteralTokenValueEndIndexDeterminer::class.java)
    private val wordTokenizer: WordTokenizer = WordTokenizer(
        literalTokenValueEndIndexDeterminer
    )

    @Test
    fun closeParenthesesTest() {
        val word = TokenValueConstants.CLOSE_PARENTHESES.toString()
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0])
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer)
    }

    @Test
    fun openParenthesesTest() {
        val word = TokenValueConstants.OPEN_PARENTHESES.toString()
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0])
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer)
    }

    @Test
    fun literalTokenValueTest() {
        val word = "ABC"
        val pos = word.length
        Mockito.`when`(
            literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
                word,
                0
            )
        ).thenReturn(pos)
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0])
    }
}