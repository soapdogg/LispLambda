package lambda.tokenizer.internal

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
        Mockito.`when`(wordTokenizer.tokenizeWord(word1)).thenReturn(listOf(word1))
        Mockito.`when`(wordTokenizer.tokenizeWord(word2)).thenReturn(listOf(word2))

        val actual = inputTokenizer.tokenizeInput(line)
        Assertions.assertEquals(2, actual.size)
        Assertions.assertEquals(word1, actual[0])
        Assertions.assertEquals(word2, actual[1])
    }
}