package lambda.printer

import lambda.core.constants.ReservedValuesConstants
import lambda.core.constants.TokenValueConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ListNotationPrinterImplTest {

    private val listNotationPrinter = ListNotationPrinter.newInstance()

    @Test
    fun printNonEmptyListOfNodesTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(atomNode.value).thenReturn(value)
        val nodes = listOf(atomNode)
        val expected = """$value${ReservedValuesConstants.NEW_LINE}"""
        val actual = listNotationPrinter.printInListNotation(nodes)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printExpressionListOfSizeTwoTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)

        val c0 = Mockito.mock(AtomNode::class.java)
        val c0Value = "c0"
        Mockito.`when`(c0.value).thenReturn(c0Value)

        val c1 = Mockito.mock(AtomNode::class.java)
        val c1Value = "c1"
        Mockito.`when`(c1.value).thenReturn(c1Value)

        Mockito.`when`(node.children).thenReturn(listOf(c0, c1))

        val expected = (
            TokenValueConstants.OPEN_PARENTHESES
            + c0Value
            + ReservedValuesConstants.LIST_DELIMITER
            + c1Value
            + TokenValueConstants.CLOSE_PARENTHESES
        )

        val actual = listNotationPrinter.printInListNotation(node)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printExpressionListOfSizeTwoLastElementIsNilTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)

        val c0 = Mockito.mock(AtomNode::class.java)
        val c0Value = "c0"
        Mockito.`when`(c0.value).thenReturn(c0Value)

        val c1 = Mockito.mock(AtomNode::class.java)
        val c1Value = ReservedValuesConstants.NIL
        Mockito.`when`(c1.value).thenReturn(c1Value)

        Mockito.`when`(node.children).thenReturn(listOf(c0, c1))

        val expected = (
            TokenValueConstants.OPEN_PARENTHESES
                + c0Value
                + TokenValueConstants.CLOSE_PARENTHESES
            )

        val actual = listNotationPrinter.printInListNotation(node)

        Assertions.assertEquals(expected, actual)
    }
}