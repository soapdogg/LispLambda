package lambda.tokenizer.internal

import lambda.core.datamodels.Token
import lambda.core.datamodels.TokenKind

class TokenGenerator {

    fun generateLiteralToken(
        value: String
    ): Token {
        return Token(
            TokenKind.LITERAL_TOKEN,
            value
        )
    }
}