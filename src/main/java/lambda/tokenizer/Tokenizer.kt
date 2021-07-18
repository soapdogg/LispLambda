package lambda.tokenizer

import lambda.asserter.LineFormatAsserter
import lambda.datamodels.Token
import java.util.Scanner

class Tokenizer(
    private val scannerToLineTransformer: ScannerToLineTransformer,
    private val lineFormatAsserter: LineFormatAsserter,
    private val lineTokenizer: LineTokenizer
) {

    fun tokenize(scanner: Scanner): List<Token> {
        val lines = scannerToLineTransformer.transformScannerInputToLines(
            scanner
        )
        lines.forEach { lineFormatAsserter.assertLineFormat(it) }
        return lines.map{
            line -> lineTokenizer.tokenizeLine(line)
        }.flatMap { it.toList() }
    }
}