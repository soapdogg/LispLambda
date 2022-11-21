package lambda

import lambda.core.datamodels.Node

interface FunctionLengthAsserter {
    fun assertLengthIsAsExpected(
        functionName: String,
        expected: Int,
        node: Node
    )

    fun assertLengthIsAtLeastMinimum(
        functionName: String,
        expected: Int,
        node: Node
    )
}