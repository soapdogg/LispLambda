package lambda.function

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NotEqFunctionTest {

    private val params = Stack<Node>()

    private val notEqFunction = NotEqFunction()

    @Test
    fun equalNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(first.value).thenReturn(value)

        val second = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(second.value).thenReturn(value)

        params.push(second)
        params.push(first)

        val actual = notEqFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }

    @Test
    fun notEqualNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(first.value).thenReturn(value)

        val second = Mockito.mock(AtomNode::class.java)
        val secondValue = "secondValue"
        Mockito.`when`(second.value).thenReturn(secondValue)

        params.push(second)
        params.push(first)

        val actual = notEqFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun oneValueTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val firstValue = "firstValue"
        Mockito.`when`(first.value).thenReturn(firstValue)

        params.push(first)


        val actual = notEqFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }
}