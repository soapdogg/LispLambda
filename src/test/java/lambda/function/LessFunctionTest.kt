package lambda.function

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.function.internal.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class LessFunctionTest {

    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)

    private val params = Stack<NodeV2>()

    private val lessFunction = LessFunction(
        numericValueRetriever
    )

    @Test
    fun evaluateLessFunctionTest() {
        val first = Mockito.mock(NodeV2::class.java)
        val second = Mockito.mock(NodeV2::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.LESS,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.LESS,
                2
            )
        ).thenReturn(secondNumeric)


        val actual = lessFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.NIL, (actual as AtomNode).value)
    }

    @Test
    fun evaluateMultipleValuesTest() {
        val first = Mockito.mock(NodeV2::class.java)
        val second = Mockito.mock(NodeV2::class.java)
        val third = Mockito.mock(NodeV2::class.java)

        params.push(third)
        params.push(second)
        params.push(first)

        val firstNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.LESS,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 2
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.LESS,
                2
            )
        ).thenReturn(secondNumeric)

        val thirdNumeric = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                third,
                FunctionNameConstants.LESS,
                3
            )
        ).thenReturn(thirdNumeric)

        val actual = lessFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }

    @Test
    fun evaluateOneValueTest() {
        val first = Mockito.mock(NodeV2::class.java)

        params.push(first)

        val firstNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.LESS,
                1
            )
        ).thenReturn(firstNumeric)

        val actual = lessFunction.evaluate(
            params
        )

        Assertions.assertEquals(ReservedValuesConstants.T, (actual as AtomNode).value)
    }
}