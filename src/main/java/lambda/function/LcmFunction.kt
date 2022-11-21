package lambda.function

import lambda.Function
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack

internal class LcmFunction(
    private val numericValueRetriever: NumericValueRetriever,
    private val gcdCalculator: GcdCalculator
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
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
            val numericAbs = kotlin.math.abs(numeric)

            val gcd = gcdCalculator.calculateGCD(result, numericAbs)
            result = (result * numericAbs) / gcd
            ++current
        }

        return AtomNode(result)
    }

}