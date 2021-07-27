package lambda.function

import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.Stack

interface Function {

    fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2
}