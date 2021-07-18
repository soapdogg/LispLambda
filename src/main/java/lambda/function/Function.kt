package lambda.function

import lambda.datamodels.NodeV2
import lambda.datamodels.Stack

interface Function {

    fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2
}