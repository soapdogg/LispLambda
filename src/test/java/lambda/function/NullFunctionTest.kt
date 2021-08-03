package lambda.function

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NullFunctionTest {

    private val params = Stack<Node>()

    private val nullFunction = NullFunction()

    @Test
    fun evaluatedAtomIsNilTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        Mockito.`when`(first.value).thenReturn(ReservedValuesConstants.NIL)

        val actual = nullFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun firstIsExpressionListTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        params.push(first)


        val actual = nullFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }
}