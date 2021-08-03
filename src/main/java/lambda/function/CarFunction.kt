package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.function.internal.ListValueRetriever

class CarFunction(
    private val listValueRetriever: ListValueRetriever
): Function {
    override fun evaluate(
        params: Stack<Node>
    ): Node {
        val first = params.pop()

        val firstExpressionListNode = listValueRetriever.retrieveListValue(
            first,
            FunctionNameConstants.CAR
        )
        return firstExpressionListNode.children[0]
    }
}