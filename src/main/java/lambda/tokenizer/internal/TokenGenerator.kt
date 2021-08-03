package lambda.tokenizer.internal

import lambda.core.datamodels.Token

class TokenGenerator {

    fun generateLiteralToken(
        value: String
    ): Token {
        return Token(
            value
        )
    }
}