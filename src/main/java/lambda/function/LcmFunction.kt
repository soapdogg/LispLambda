package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack
import lambda.function.internal.GcdCalculator
import lambda.function.internal.NumericValueRetriever

class LcmFunction(
    private val numericValueRetriever: NumericValueRetriever,
    private val gcdCalculator: GcdCalculator
): Function  {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.LCM,
            1
        )
        var result = kotlin.math.abs(firstNumeric)
        var current = 2

        while(params.isNotEmpty()) {
            val numeric = numericValueRetriever.retrieveNumericValue(
                params.pop(),
                FunctionNameConstants.LCM,
                current
            )
            var numericAbs = kotlin.math.abs(numeric)

            val gcd = gcdCalculator.calculateGCD(result, numericAbs)
            result = (result * numericAbs) / gcd
            ++current
        }

        return AtomNode(result)
    }

}