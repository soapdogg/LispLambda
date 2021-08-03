package lambda.function.internal

import lambda.core.datamodels.*
import lambda.core.exceptions.NotAListException

class ListValueRetriever {
    fun retrieveListValue(
        node: Node,
        functionName: String
    ): ExpressionListNode {
        if (node is ExpressionListNode) {
            return node
        }
        val atomNode = node as AtomNode
        val sb = """Error! Parameter of $functionName is not a list.    Actual: ${atomNode.value}${'\n'}"""
        throw NotAListException(sb)
    }
}