package lambda.singleton

import lambda.tokenizer.Tokenizer
import lambda.tokenizer.internal.*

enum class TokenizerSingleton {
    INSTANCE;

    private val wordTokenizer = WordTokenizer()

    val tokenizer= Tokenizer(
        wordTokenizer
    )
}