package lambda.function

import lambda.core.datamodels.*
import lambda.core.exceptions.NotAListException

internal interface ListValueRetriever {
    fun retrieveListValue(node: Node, functionName: String): ExpressionListNode
}

internal class ListValueRetrieverImpl: ListValueRetriever {
    override fun retrieveListValue(
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