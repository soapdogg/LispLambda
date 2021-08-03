package lambda.tokenizer.internal

import java.util.LinkedList
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.Token

class WordTokenizer (
    private val tokenGenerator: TokenGenerator,
    private val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer
){

    fun tokenizeWord(word: String): List<Token> {
        val tokens: MutableList<Token> = LinkedList()
        var startingPos = 0
        while (startingPos < word.length) {
            val token: Token = when (word[startingPos]) {
                TokenValueConstants.CLOSE_PARENTHESES -> {
                    Token(
                        TokenValueConstants.CLOSE_PARENTHESES.toString()
                    )
                }
                TokenValueConstants.OPEN_PARENTHESES -> {
                    Token(
                        TokenValueConstants.OPEN_PARENTHESES.toString()
                    )
                }
                else -> {
                    val pos = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
                        word,
                        startingPos
                    )
                    val fragment = word.substring(startingPos, pos)
                    tokenGenerator.generateLiteralToken(
                        fragment
                    )
                }
            }
            startingPos += token.value.length
            tokens.add(token)
        }
        return tokens
    }
}