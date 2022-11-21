package lambda.function

import lambda.Function
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import kotlin.math.pow

internal class ExptFunction (
    private val numericValueRetriever: NumericValueRetriever
): Function {
    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val baseNumber = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.EXPT,
            1
        )
        val powerNumber = numericValueRetriever.retrieveNumericValue(
            params.pop(),
            FunctionNameConstants.EXPT,
            2
        )
        val value = baseNumber.toDouble().pow(powerNumber)
        return AtomNode(value.toInt())
    }
}