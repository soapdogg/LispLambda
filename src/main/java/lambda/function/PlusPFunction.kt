package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever

class PlusPFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {
    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val numeric = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.PLUS_P,
            1
        )
        val isPositive = numeric > 0
        return AtomNode(isPositive)
    }
}