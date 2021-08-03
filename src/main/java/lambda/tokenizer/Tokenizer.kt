package lambda.tokenizer

import lambda.core.constants.TokenValueConstants
import lambda.tokenizer.internal.WordTokenizer

class Tokenizer(
    private val wordTokenizer: WordTokenizer
) {

    fun tokenize(input: String): List<String> {
        val trimmedLine = input.trim { it <= ' ' }
        val words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN.toRegex())
        return words.map {
                word -> wordTokenizer.tokenizeWord(word)
        }.flatMap { it.toList() }
    }
}