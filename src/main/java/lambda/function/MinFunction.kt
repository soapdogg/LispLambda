package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever

class MinFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        var result = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.MIN,
            1
        )

        var current = 2
        while (params.isNotEmpty()) {
            val numeric = numericValueRetriever.retrieveNumericValue(
                params.pop(),
                FunctionNameConstants.MIN,
                current
            )
            if (numeric < result) {
                result = numeric
            }
            ++current
        }

        return AtomNode(result)
    }

}