package lambda.tokenizer

import lambda.tokenizer.internal.InputFormatAsserter
import lambda.core.datamodels.Token
import lambda.tokenizer.internal.InputTokenizer

class Tokenizer(
    private val inputFormatAsserter: InputFormatAsserter,
    private val inputTokenizer: InputTokenizer
) {

    fun tokenize(input: String): List<Token> {
        inputFormatAsserter.assertInputFormat(input)
        return inputTokenizer.tokenizeInput(input)
    }
}