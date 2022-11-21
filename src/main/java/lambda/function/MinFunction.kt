package lambda.function

import lambda.Function
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack

internal class MinFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
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