package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import lambda.function.internal.GcdCalculator
import lambda.function.internal.NumericValueRetriever

class GcdFunction(
    private val numericValueRetriever: NumericValueRetriever,
    private val gcdCalculator: GcdCalculator
): Function {
    override fun evaluate(
        params: Stack<Node>
    ): Node {
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

            result = gcdCalculator.calculateGCD(result, numericAbs)
            ++current
        }
        return AtomNode(result)
    }
}