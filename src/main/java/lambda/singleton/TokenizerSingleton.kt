package lambda.singleton

import lambda.core.constants.TokenValueConstants
import lambda.tokenizer.Tokenizer
import lambda.tokenizer.internal.*

enum class TokenizerSingleton {
    INSTANCE;

    private val inputFormatAsserter = InputFormatAsserter(
        TokenValueConstants.ERROR_STATE_PATTERN
    )

    private val literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer()

    private val tokenGenerator= TokenGenerator()

    private val wordTokenizer= WordTokenizer(
        tokenGenerator,
        literalTokenValueEndIndexDeterminer
    )
    private val inputTokenizer= InputTokenizer(
        wordTokenizer
    )
    val tokenizer= Tokenizer(
        inputFormatAsserter,
        inputTokenizer
    )

}