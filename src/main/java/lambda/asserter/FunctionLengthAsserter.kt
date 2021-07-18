package lambda.asserter

import lambda.datamodels.NodeV2
import lambda.determiner.FunctionLengthDeterminer
import lambda.exceptions.WrongFunctionLengthException

class FunctionLengthAsserter (
    private val functionLengthDeterminer: FunctionLengthDeterminer
){
    fun assertLengthIsAsExpected(
        functionName: String,
        expected: Int,
        node: NodeV2
    ) {
        val actual = functionLengthDeterminer.determineFunctionLength(node)
        if (actual != expected) {
            val sb = """Error! Expected length of $functionName list is $expected!    Actual: ${actual}${'\n'}"""
            throw WrongFunctionLengthException(sb)
        }
    }
}