package lambda.function

import lambda.ListNotationPrinter
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.exceptions.NotNumericException

internal interface NumericValueRetriever {
    fun retrieveNumericValue(
        node: Node,
        functionName: String,
        index: Int
    ): Int
}

internal class NumericValueRetrieverImpl(
    private val listNotationPrinter: ListNotationPrinter
): NumericValueRetriever {

    override fun retrieveNumericValue(
        node: Node,
        functionName: String,
        index: Int
    ): Int {
        if (node is AtomNode) {
            val isNumeric = node.value.matches(Regex(ReservedValuesConstants.NUMERIC_PATTERN))
            if (isNumeric) {
                return node.value.toInt()
            }
        }
        val value = listNotationPrinter.printInListNotation(node)
        val sb = """Error! Parameter at position: $index of function $functionName is not numeric!    Actual: $value${'\n'}"""
        throw NotNumericException(sb)
    }
}