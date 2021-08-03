package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.function.internal.NumericValueRetriever

class PlusFunction(
    private val numericValueRetriever: NumericValueRetriever
): Function {

    override fun evaluate(
        params: Stack<Node>
    ): Node {
        var result = 0
        var current = 1
        while(params.isNotEmpty()){
            val numeric = numericValueRetriever.retrieveNumericValue(
                params.pop(),
                FunctionNameConstants.PLUS,
                current
            )
            result += numeric
            ++current
        }

        return AtomNode(result)
    }
}