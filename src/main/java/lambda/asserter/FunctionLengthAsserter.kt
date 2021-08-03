package lambda.asserter

import lambda.core.datamodels.Node
import lambda.core.exceptions.WrongFunctionLengthException

class FunctionLengthAsserter (
    private val functionLengthDeterminer: FunctionLengthDeterminer
){
    fun assertLengthIsAsExpected(
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

    fun assertLengthIsAtLeastMinimum(
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