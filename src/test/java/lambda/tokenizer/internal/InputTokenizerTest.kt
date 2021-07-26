package lambda.tokenizer.internal

import lambda.datamodels.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class InputTokenizerTest {
    private val word1: String = "word1"
    private val word2: String = "word2"
    private val line: String = "\t$word1    $word2\n"
    private val wordTokenizer: WordTokenizer = Mockito.mock(WordTokenizer::class.java)
    private val inputTokenizer: InputTokenizer = InputTokenizer(
        wordTokenizer
    )

    @Test
    fun tokenizeLineTest() {
        val word1Token = Mockito.mock(Token::class.java)
        val word1Tokens = listOf(word1Token)
        Mockito.`when`(wordTokenizer.tokenizeWord(word1)).thenReturn(word1Tokens)

        val word2Token = Mockito.mock(Token::class.java)
        val word2Tokens = listOf(word2Token)
        Mockito.`when`(wordTokenizer.tokenizeWord(word2)).thenReturn(word2Tokens)

        val actual = inputTokenizer.tokenizeInput(line)
        Assertions.assertEquals(2, actual.size)
        Assertions.assertEquals(word1Token, actual[0])
        Assertions.assertEquals(word2Token, actual[1])
    }
}