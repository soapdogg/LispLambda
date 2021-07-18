package lambda.singleton

import lambda.tokenizer.LineTokenizer
import lambda.tokenizer.ScannerToLineTransformer
import lambda.tokenizer.Tokenizer
import lambda.tokenizer.WordTokenizer

enum class TokenizerSingleton {
    INSTANCE;

    val scannerToLineTransformer: ScannerToLineTransformer = ScannerToLineTransformer()
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
            scannerToLineTransformer,
            AsserterSingleton.INSTANCE.lineFormatAsserter,
            lineTokenizer
        )
    }
}