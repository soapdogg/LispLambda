package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack
import lambda.function.internal.NumericValueRetriever

class GcdFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        if (params.isEmpty()) {
            return AtomNode(0)
        }
        val first = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.GCD,
            1
        )
        var result = kotlin.math.abs(firstNumeric)
        var current = 2
        while(params.isNotEmpty()) {
            val numeric = numericValueRetriever.retrieveNumericValue(
                params.pop(),
                FunctionNameConstants.GCD,
                current
            )
            var numericAbs = kotlin.math.abs(numeric)

            while (result != numericAbs) {
                if (result > numericAbs)
                    result -= numericAbs
                else
                    numericAbs -= result
            }
            ++current
        }
        return AtomNode(result)
    }
}