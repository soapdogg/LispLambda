package lambda.asserter

import lambda.core.datamodels.Node
import lambda.core.exceptions.WrongFunctionLengthException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FunctionLengthAsserterTest {
    private val functionName = "functionName"
    private val expected = 2
    private val node = Mockito.mock(Node::class.java)
    private val actual = 1
    private val functionLengthDeterminer = Mockito.mock(FunctionLengthDeterminer::class.java)
    private val functionLengthAsserter = FunctionLengthAsserterImpl(
        functionLengthDeterminer
    )

    @Test
    fun equalsTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(node)).thenReturn(actual)
        Assertions.assertDoesNotThrow {
            functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                actual,
                node
            )
        }
    }

    @Test
    fun doesNotEqualTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(node)).thenReturn(actual)
        Assertions.assertThrows(
            WrongFunctionLengthException::class.java
        ) {
            functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected,
                node
            )
        }
    }

    @Test
    fun atLeastMinimumTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(node)).thenReturn(expected)
        Assertions.assertDoesNotThrow{
            functionLengthAsserter.assertLengthIsAtLeastMinimum(
                functionName,
                expected,
                node
            )
        }
    }

    @Test
    fun notAtLeastMinimumTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(node)).thenReturn(actual)
        Assertions.assertThrows(
            WrongFunctionLengthException::class.java
        ) {
            functionLengthAsserter.assertLengthIsAtLeastMinimum(
                functionName,
                expected,
                node
            )
        }
    }
}