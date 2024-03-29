package lambda.function

import lambda.Function
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack

internal class EvenPFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val numeric = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.EVEN_P,
            1
        )
        val isEven = numeric % 2 == 0
        return AtomNode(isEven)
    }
}