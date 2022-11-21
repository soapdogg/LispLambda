package lambda

import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack

interface Function {

    fun evaluate(
        params: Stack<Node>
    ): Node
}