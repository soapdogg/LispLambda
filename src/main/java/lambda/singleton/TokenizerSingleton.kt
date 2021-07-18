package lambda.singleton

import lambda.tokenizer.LineTokenizer
import lambda.tokenizer.Tokenizer
import lambda.tokenizer.WordTokenizer

enum class TokenizerSingleton {
    INSTANCE;

    val wordTokenizer: WordTokenizer = WordTokenizer(
        GeneratorSingleton.INSTANCE.tokenGenerator,
        DeterminerSingleton.INSTANCE.numericTokenValueEndIndexDeterminer,
        DeterminerSingleton.INSTANCE.literalTokenValueEndIndexDeterminer
    )
    val lineTokenizer: LineTokenizer
    val tokenizer: Tokenizer

    init {
        lineTokenizer = LineTokenizer(
            wordTokenizer
        )
        tokenizer = Tokenizer(
            AsserterSingleton.INSTANCE.lineFormatAsserter,
            lineTokenizer
        )
    }
}