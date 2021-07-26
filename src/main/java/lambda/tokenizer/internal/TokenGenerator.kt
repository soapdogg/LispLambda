package lambda.tokenizer.internal

import lambda.constants.TokenValueConstants
import lambda.datamodels.Token
import lambda.datamodels.TokenKind

class TokenGenerator {
    fun generateCloseToken(): Token {
        return Token(
            TokenKind.CLOSE_TOKEN,
            TokenValueConstants.CLOSE_PARENTHESES.toString()
        )
    }

    fun generateOpenToken(): Token {
        return Token(
            TokenKind.OPEN_TOKEN,
            TokenValueConstants.OPEN_PARENTHESES.toString()
        )
    }

    fun generateNumericToken(
        value: String
    ): Token {
        return Token(
            TokenKind.NUMERIC_TOKEN,
            value
        )
    }

    fun generateLiteralToken(
        value: String
    ): Token {
        return Token(
            TokenKind.LITERAL_TOKEN,
            value
        )
    }
}