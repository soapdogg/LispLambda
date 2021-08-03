package lambda.tokenizer.internal

import java.util.LinkedList
import lambda.core.constants.TokenValueConstants

class WordTokenizer{

    fun tokenizeWord(word: String): List<String> {
        val tokens: MutableList<String> = LinkedList()
        var startingPos = 0
        while (startingPos < word.length) {
            val token: String = when (word[startingPos].toString()) {
                TokenValueConstants.CLOSE_PARENTHESES -> {
                    TokenValueConstants.CLOSE_PARENTHESES
                }
                TokenValueConstants.OPEN_PARENTHESES -> {
                    TokenValueConstants.OPEN_PARENTHESES
                }
                else -> {
                    val pos = word.indexOfAny(listOf(TokenValueConstants.OPEN_PARENTHESES, TokenValueConstants.CLOSE_PARENTHESES), startingPos)
                    val endIndex = if (pos == -1) word.length else pos
                    word.substring(startingPos, endIndex)
                }
            }
            startingPos += token.length
            tokens.add(token)
        }
        return tokens
    }
}