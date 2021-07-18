package lambda.valueretriver

import lambda.datamodels.*
import lambda.exceptions.NotAListException
import lambda.printer.DotNotationPrinter

class ListValueRetriever(
    private val dotNotationPrinter: DotNotationPrinter
){
    fun retrieveListValue(
        node: NodeV2,
        functionName: String
    ): ExpressionListNode {
        if (node is ExpressionListNode) {
            return node
        }
        val sb = """Error! Parameter of $functionName is not a list.    Actual: ${dotNotationPrinter.printInDotNotation(node)}${'\n'}"""
        throw NotAListException(sb)
    }
}