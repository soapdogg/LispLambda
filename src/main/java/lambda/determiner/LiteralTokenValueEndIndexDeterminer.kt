package lambda.determiner

import lambda.constants.TokenValueConstants

class LiteralTokenValueEndIndexDeterminer {
    fun determineEndIndexOfLiteralTokenValue(
        word: String,
        startingPos: Int
    ): Int {
        val wordLength = word.length
        val firstParenthesesIndex = IntRange(startingPos, wordLength - 1)
            .firstOrNull { i: Int ->
                word[i] == TokenValueConstants.OPEN_PARENTHESES || word[i] == TokenValueConstants.CLOSE_PARENTHESES
            }
        val endIndex = firstParenthesesIndex ?: Integer.MAX_VALUE
        return wordLength.coerceAtMost(endIndex)
    }
}