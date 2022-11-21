package lambda.asserter

import lambda.FunctionLengthAsserter
import lambda.core.datamodels.Node
import lambda.core.exceptions.WrongFunctionLengthException

internal class FunctionLengthAsserterImpl (
    private val functionLengthDeterminer: FunctionLengthDeterminer
): FunctionLengthAsserter {
    override fun assertLengthIsAsExpected(
        functionName: String,
        expected: Int,
        node: Node
    ) {
        val actual = functionLengthDeterminer.determineFunctionLength(node)
        if (actual != expected) {
            val sb = """Error! Expected length of $functionName list is $expected!    Actual: ${actual}${'\n'}"""
            throw WrongFunctionLengthException(sb)
        }
    }

    override fun assertLengthIsAtLeastMinimum(
        functionName: String,
        expected: Int,
        node: Node
    ) {
        val actual = functionLengthDeterminer.determineFunctionLength(node)
        if (actual < expected) {
            val sb = """Error! Expected length of $functionName list to be at least $expected!    Actual: ${actual}${'\n'}"""
            throw WrongFunctionLengthException(sb)
        }
    }
}