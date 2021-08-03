package lambda.singleton

import lambda.tokenizer.Tokenizer
import lambda.tokenizer.internal.*

enum class TokenizerSingleton {
    INSTANCE;

    private val literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer()

    private val wordTokenizer = WordTokenizer(
        literalTokenValueEndIndexDeterminer
    )

    val tokenizer= Tokenizer(
        wordTokenizer
    )
}