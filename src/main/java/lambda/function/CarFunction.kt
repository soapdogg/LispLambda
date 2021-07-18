package lambda.function

import lambda.constants.FunctionNameConstants
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.valueretriver.ListValueRetriever

class CarFunction(
    private val listValueRetriever: ListValueRetriever
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()

        val firstExpressionListNode = listValueRetriever.retrieveListValue(
            first,
            FunctionNameConstants.CAR
        )
        return firstExpressionListNode.children[0]
    }
}