package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.math.pow

class ExptFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<Node>()

    private val exptFunction = ExptFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateExptFunctionTest(){
        val node = Mockito.mock(Node::class.java)
        val node2 = Mockito.mock(Node::class.java)

        params.push(node2)
        params.push(node)

        val baseNumeric = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                node,
                FunctionNameConstants.EXPT,
                1
            )
        ).thenReturn(baseNumeric)

        val powerNumeric = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                node2,
                FunctionNameConstants.EXPT,
                2
            )
        ).thenReturn(powerNumeric)

        val expected = baseNumeric.toDouble().pow(powerNumeric).toInt().toString()
        val actual = exptFunction.evaluate(params)


        Assertions.assertEquals(expected, (actual as AtomNode).value)
    }
}