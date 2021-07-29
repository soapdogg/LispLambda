package lambda.asserter

import lambda.core.datamodels.NodeV2
import lambda.core.exceptions.WrongFunctionLengthException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FunctionLengthAsserterTest {
    private val functionName = "functionName"
    private val expected = 2
    private val nodeV2 = Mockito.mock(NodeV2::class.java)
    private val actual = 1
    private val functionLengthDeterminer = Mockito.mock(FunctionLengthDeterminer::class.java)
    private val functionLengthAsserter = FunctionLengthAsserter(
        functionLengthDeterminer
    )

    @Test
    fun equalsTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(nodeV2)).thenReturn(actual)
        Assertions.assertDoesNotThrow {
            functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                actual,
                nodeV2
            )
        }
    }

    @Test
    fun doesNotEqualTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(nodeV2)).thenReturn(actual)
        Assertions.assertThrows(
            WrongFunctionLengthException::class.java
        ) {
            functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected,
                nodeV2
            )
        }
    }

    @Test
    fun atLeastMinimumTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(nodeV2)).thenReturn(expected)
        Assertions.assertDoesNotThrow{
            functionLengthAsserter.assertLengthIsAtLeastMinimum(
                functionName,
                expected,
                nodeV2
            )
        }
    }

    @Test
    fun notAtLeastMinimumTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(nodeV2)).thenReturn(actual)
        Assertions.assertThrows(
            WrongFunctionLengthException::class.java
        ) {
            functionLengthAsserter.assertLengthIsAtLeastMinimum(
                functionName,
                expected,
                nodeV2
            )
        }
    }
}