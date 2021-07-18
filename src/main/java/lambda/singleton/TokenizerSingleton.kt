package lambda.singleton

import lambda.tokenizer.InputTokenizer
import lambda.tokenizer.Tokenizer
import lambda.tokenizer.WordTokenizer

enum class TokenizerSingleton {
    INSTANCE;

    val wordTokenizer: WordTokenizer = WordTokenizer(
        GeneratorSingleton.INSTANCE.tokenGenerator,
        DeterminerSingleton.INSTANCE.numericTokenValueEndIndexDeterminer,
        DeterminerSingleton.INSTANCE.literalTokenValueEndIndexDeterminer
    )
    val inputTokenizer: InputTokenizer
    val tokenizer: Tokenizer

    init {
        inputTokenizer = InputTokenizer(
            wordTokenizer
        )
        tokenizer = Tokenizer(
            AsserterSingleton.INSTANCE.inputFormatAsserter,
            inputTokenizer
        )
    }
}