package lambda.tokenizer

import lambda.Tokenizer

enum class TokenizerSingleton {
    INSTANCE;

    fun getTokenizer(): Tokenizer {
        val wordTokenizer = WordTokenizerImpl()

        return TokenizerImpl(
            wordTokenizer
        )
    }
}