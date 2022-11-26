package lambda.function

import lambda.ListNotationPrinter
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.exceptions.NotNumericException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NumericValueRetrieverTest {
    private val functionName = FunctionNameConstants.QUOTE
    private val index = 1
    private val listNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val numericValueRetriever = NumericValueRetrieverImpl(
        listNotationPrinter
    )

    @Test
    fun nodeIsNotNumericTest() {
        val node = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(node.value).thenReturn(value)
        Assertions.assertThrows(
            NotNumericException::class.java
        ) {
            numericValueRetriever.retrieveNumericValue(
                node,
                functionName,
                index
            )
        }
    }

    @Test
    fun nodeIsNumericTest() {
        val value = 34
        val node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.value).thenReturn(value.toString())
        val actual = numericValueRetriever.retrieveNumericValue(
            node,
            functionName,
            index
        )
        Assertions.assertEquals(value, actual)
    }
}