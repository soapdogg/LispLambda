package lambda.valueretriver

import lambda.datamodels.*
import lambda.exceptions.NotAListException
import lambda.printer.DotNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class ListValueRetrieverTest {
    private val functionName = "functionName"
    private val dotNotationPrinter = Mockito.mock(DotNotationPrinter::class.java)
    private val listValueRetriever = ListValueRetriever(
        dotNotationPrinter
    )

    @Test
    fun inputIsAListTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val actual = listValueRetriever.retrieveListValue(
            node,
            functionName
        )
        Assertions.assertEquals(node, actual)
        Mockito.verifyNoInteractions(dotNotationPrinter)
    }


    @Test
    fun inputIsNotAVariableTest() {
        val node = Mockito.mock(AtomNode::class.java)
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)

        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            listValueRetriever.retrieveListValue(
                node,
                functionName
            )
        }
    }
}