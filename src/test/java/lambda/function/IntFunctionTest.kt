package lambda.function

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IntFunctionTest {
    private val params = Stack<Node>()

    private val intFunction = IntFunction()

    @Test
    fun evaluatedAtomIsNumericTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        val numeric = 10
        Mockito.`when`(first.value).thenReturn(numeric.toString())

        val actual = intFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun firstIsExpressionListTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        params.push(first)

        val actual = intFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }
}