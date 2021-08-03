package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.function.internal.NumericValueRetriever

class LessFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val first = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.LESS,
            1
        )

        var result = firstNumeric
        var current = 2
        while(params.isNotEmpty()) {
            val numeric = numericValueRetriever.retrieveNumericValue(
                params.pop(),
                FunctionNameConstants.LESS,
                current
            )
            if (result >= numeric) {
                return AtomNode(false)
            }
            result = numeric
            ++current
        }

        return AtomNode(true)
    }
}