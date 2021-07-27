package lambda.tokenizer.internal

import java.util.LinkedList
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.Token

class WordTokenizer (
    private val tokenGenerator: TokenGenerator,
    private val numericTokenValueEndIndexDeterminer: NumericTokenValueEndIndexDeterminer,
    private val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer
){

    fun tokenizeWord(word: String): List<Token> {
        val tokens: MutableList<Token> = LinkedList()
        var startingPos = 0
        while (startingPos < word.length) {
            val currentChar = word[startingPos]
            val token: Token = when {
                currentChar == TokenValueConstants.CLOSE_PARENTHESES -> {
                    tokenGenerator.generateCloseToken()
                }
                currentChar == TokenValueConstants.OPEN_PARENTHESES -> {
                    tokenGenerator.generateOpenToken()
                }
                Character.isDigit(currentChar) -> {
                    val pos = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
                        word,
                        startingPos
                    )
                    val fragment = word.substring(startingPos, pos)
                    tokenGenerator.generateNumericToken(
                        fragment
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