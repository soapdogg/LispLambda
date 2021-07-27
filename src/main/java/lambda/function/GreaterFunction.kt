package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NodeGenerator
import lambda.function.internal.NumericValueRetriever

class GreaterFunction(
    private val numericValueRetriever: NumericValueRetriever,
    private val nodeGenerator: NodeGenerator
) : Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.GREATER,
            1
        )
        val secondNumeric = numericValueRetriever.retrieveNumericValue(
            second,
            FunctionNameConstants.GREATER,
            2
        )

        val result = firstNumeric > secondNumeric

        return nodeGenerator.generateAtomNode(result)
    }
}