package lambda.singleton

import lambda.constants.TokenValueConstants
import lambda.tokenizer.Tokenizer
import lambda.tokenizer.internal.*

enum class TokenizerSingleton {
    INSTANCE;

    private val inputFormatAsserter = InputFormatAsserter(
        TokenValueConstants.ERROR_STATE_PATTERN
    )

    private val numericTokenValueEndIndexDeterminer = NumericTokenValueEndIndexDeterminer()
    private val literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer()

    private val tokenGenerator: TokenGenerator = TokenGenerator()

    private val wordTokenizer: WordTokenizer = WordTokenizer(
        tokenGenerator,
        numericTokenValueEndIndexDeterminer,
        literalTokenValueEndIndexDeterminer
    )
    private val inputTokenizer: InputTokenizer = InputTokenizer(
        wordTokenizer
    )
    val tokenizer: Tokenizer = Tokenizer(
        inputFormatAsserter,
        inputTokenizer
    )

}