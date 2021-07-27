package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NodeGenerator
import lambda.function.internal.NumericValueRetriever

class PlusFunction(
    private val numericValueRetriever: NumericValueRetriever,
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.PLUS,
            1
        )
        val secondNumeric = numericValueRetriever.retrieveNumericValue(
            second,
            FunctionNameConstants.PLUS,
            2
        )

        val result = firstNumeric + secondNumeric

        return nodeGenerator.generateAtomNode(result)
    }
}