package lambda.tokenizer

import lambda.asserter.LineFormatAsserter
import lambda.datamodels.Token
import java.util.Scanner

class Tokenizer(
    private val lineFormatAsserter: LineFormatAsserter,
    private val lineTokenizer: LineTokenizer
) {

    fun tokenize(lines: List<String>): List<Token> {
        lines.forEach { lineFormatAsserter.assertLineFormat(it) }
        return lines.map{
            line -> lineTokenizer.tokenizeLine(line)
        }.flatMap { it.toList() }
    }
}