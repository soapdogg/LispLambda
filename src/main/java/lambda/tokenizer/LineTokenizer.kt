package lambda.tokenizer

import lambda.constants.TokenValueConstants
import lambda.datamodels.Token

class LineTokenizer (
    private val wordTokenizer: WordTokenizer
){

    fun tokenizeLine(
        line: String
    ): List<Token> {
        val trimmedLine = line.trim { it <= ' ' }
        val words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN.toRegex())
        return words.map {
            word -> wordTokenizer.tokenizeWord(word)
        }.flatMap { it.toList() }
    }
}