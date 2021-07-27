package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NumericValueRetriever

class LessFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.LESS,
            1
        )
        val secondNumeric = numericValueRetriever.retrieveNumericValue(
            second,
            FunctionNameConstants.LESS,
            2
        )

        val result = firstNumeric < secondNumeric

        return AtomNode(result)
    }
}