package lambda.function

import lambda.Function
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack

internal class ExpFunction (
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val numeric = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.EXP,
            1
        )
        val value = kotlin.math.exp(numeric.toDouble())
        return AtomNode(value.toInt())
    }
}