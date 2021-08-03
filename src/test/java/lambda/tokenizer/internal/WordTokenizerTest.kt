package lambda.tokenizer.internal

import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class WordTokenizerTest {
    private val tokenGenerator: TokenGenerator = Mockito.mock(TokenGenerator::class.java)
    private val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer = Mockito.mock(
        LiteralTokenValueEndIndexDeterminer::class.java)
    private val wordTokenizer: WordTokenizer = WordTokenizer(
        tokenGenerator,
        literalTokenValueEndIndexDeterminer
    )

    @Test
    fun closeParenthesesTest() {
        val word = TokenValueConstants.CLOSE_PARENTHESES.toString()
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0].value)
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer)
    }

    @Test
    fun openParenthesesTest() {
        val word = TokenValueConstants.OPEN_PARENTHESES.toString()
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(word, actual[0].value)
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
        val literalToken = Mockito.mock(Token::class.java)
        Mockito.`when`(literalToken.value).thenReturn(word)
        Mockito.`when`(tokenGenerator.generateLiteralToken(word)).thenReturn(literalToken)
        val actual = wordTokenizer.tokenizeWord(word)
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(literalToken, actual[0])
    }
}