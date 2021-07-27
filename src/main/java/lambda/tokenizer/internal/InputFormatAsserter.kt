package lambda.tokenizer.internal

import lambda.core.exceptions.InvalidTokenException

import java.util.regex.Pattern

class InputFormatAsserter(
    private val errorStatePattern: Pattern
) {

    fun assertInputFormat(line: String) {
        val matcher = errorStatePattern.matcher(line)
        val hasErrorState = matcher.find()
        if (hasErrorState) {
            val matchResult = matcher.toMatchResult()
            val start = matchResult.start()
            val end = matchResult.end()
            val errorMessage = """
                Error! Invalid token: ${line.substring(start, end)}
                
                """.trimIndent()
            throw InvalidTokenException(errorMessage)
        }
    }
}