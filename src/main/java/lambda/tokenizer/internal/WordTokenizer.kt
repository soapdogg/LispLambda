package lambda.tokenizer.internal

import java.util.LinkedList
import lambda.core.constants.TokenValueConstants

class WordTokenizer(
    private val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer
){

    fun tokenizeWord(word: String): List<String> {
        val tokens: MutableList<String> = LinkedList()
        var startingPos = 0
        while (startingPos < word.length) {
            val token: String = when (word[startingPos]) {
                TokenValueConstants.CLOSE_PARENTHESES -> {
                    TokenValueConstants.CLOSE_PARENTHESES.toString()
                }
                TokenValueConstants.OPEN_PARENTHESES -> {
                    TokenValueConstants.OPEN_PARENTHESES.toString()
                }
                else -> {
                    val pos = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
                        word,
                        startingPos
                    )
                    word.substring(startingPos, pos)
                }
            }
            startingPos += token.length
            tokens.add(token)
        }
        return tokens
    }
}