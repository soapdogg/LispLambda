package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NumericValueRetriever

class TimesFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.TIMES,
            1
        )
        val secondNumeric = numericValueRetriever.retrieveNumericValue(
            second,
            FunctionNameConstants.TIMES,
            2
        )

        val result = firstNumeric * secondNumeric

        return AtomNode(result)
    }
}