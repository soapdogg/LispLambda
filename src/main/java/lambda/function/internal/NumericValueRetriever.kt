package lambda.function.internal

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.NodeV2
import lambda.determiner.NumericStringDeterminer
import lambda.core.exceptions.NotNumericException
import lambda.printer.ListNotationPrinter

class NumericValueRetriever(
    private val numericStringDeterminer: NumericStringDeterminer,
    private val listNotationPrinter: ListNotationPrinter
) {

    fun retrieveNumericValue(
        node: NodeV2,
        functionName: String,
        index: Int
    ): Int {
        if (node is AtomNode) {
            val isNumeric = numericStringDeterminer.isStringNumeric(node.value)
            if (isNumeric) {
                return node.value.toInt()
            }
        }
        val value = listNotationPrinter.printInListNotation(node)
        val sb = """Error! Parameter at position: $index of function $functionName is not numeric!    Actual: $value${'\n'}"""
        throw NotNumericException(sb)
    }
}