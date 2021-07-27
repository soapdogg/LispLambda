package lambda.tokenizer.internal

import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.Token

class InputTokenizer (
    private val wordTokenizer: WordTokenizer
){

    fun tokenizeInput(
        input: String
    ): List<Token> {
        val trimmedLine = input.trim { it <= ' ' }
        val words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN.toRegex())
        return words.map {
            word -> wordTokenizer.tokenizeWord(word)
        }.flatMap { it.toList() }
    }
}