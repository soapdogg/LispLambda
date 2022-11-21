package lambda.tokenizer

import lambda.Tokenizer
import lambda.core.constants.TokenValueConstants

internal class TokenizerImpl (
    private val wordTokenizer: WordTokenizer
): Tokenizer {

    override fun tokenize(input: String): List<String> {
        val trimmedLine = input.trim { it <= ' ' }
        val words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN.toRegex())
        return words.map {
                word -> wordTokenizer.tokenizeWord(word)
        }.flatMap { it.toList() }
    }
}