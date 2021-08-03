package lambda.tokenizer.internal

import lambda.core.constants.TokenValueConstants

class InputTokenizer (
    private val wordTokenizer: WordTokenizer
){

    fun tokenizeInput(
        input: String
    ): List<String> {
        val trimmedLine = input.trim { it <= ' ' }
        val words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN.toRegex())
        return words.map {
            word -> wordTokenizer.tokenizeWord(word)
        }.flatMap { it.toList() }
    }
}