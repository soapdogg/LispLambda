package lambda.function.internal

import lambda.core.datamodels.*
import lambda.core.exceptions.NotAListException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class ListValueRetrieverTest {
    private val functionName = "functionName"
    private val listValueRetriever = ListValueRetriever()

    @Test
    fun inputIsAListTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val actual = listValueRetriever.retrieveListValue(
            node,
            functionName
        )
        Assertions.assertEquals(node, actual)
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