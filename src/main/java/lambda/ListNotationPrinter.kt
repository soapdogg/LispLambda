package lambda

import lambda.core.datamodels.Node

interface ListNotationPrinter {
    fun printInListNotation(
        nodes: List<Node>
    ): String

    fun printInListNotation(node: Node):String
}