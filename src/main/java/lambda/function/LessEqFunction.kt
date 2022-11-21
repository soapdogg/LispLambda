package lambda.function

import lambda.Function
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack

internal class LessEqFunction (
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val first = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.LESS_EQ,
            1
        )

        var result = firstNumeric
        var current = 2
        while(params.isNotEmpty()) {
            val numeric = numericValueRetriever.retrieveNumericValue(
                params.pop(),
                FunctionNameConstants.LESS_EQ,
                current
            )
            if (result > numeric) {
                return AtomNode(false)
            }
            result = numeric
            ++current
        }

        return AtomNode(true)
    }
}