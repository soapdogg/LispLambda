package lambda.tokenizer

import lambda.asserter.InputFormatAsserter
import lambda.datamodels.Token

class Tokenizer(
    private val inputFormatAsserter: InputFormatAsserter,
    private val inputTokenizer: InputTokenizer
) {

    fun tokenize(input: String): List<Token> {
        inputFormatAsserter.assertInputFormat(input)
        return inputTokenizer.tokenizeInput(input)
    }
}