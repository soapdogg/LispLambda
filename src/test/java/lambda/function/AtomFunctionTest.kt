package lambda.function

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomFunctionTest {

    private val params = Stack<Node>()

    private val atomFunction = AtomFunction()

    @Test
    fun firstIsAtomTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        val actual = atomFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun firstIsExpressionListTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        params.push(first)

        val actual = atomFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }
}