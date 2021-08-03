package lambda.tokenizer

import lambda.tokenizer.internal.InputFormatAsserter
import lambda.tokenizer.internal.InputTokenizer

class Tokenizer(
    private val inputFormatAsserter: InputFormatAsserter,
    private val inputTokenizer: InputTokenizer
) {

    fun tokenize(input: String): List<String> {
        inputFormatAsserter.assertInputFormat(input)
        return inputTokenizer.tokenizeInput(input)
    }
}