package lambda.function.internal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GcdCalculatorTest {

    private val gcdCalculator = GcdCalculator()

    @Test
    fun calculateGcdTest1() {
        val actual = gcdCalculator.calculateGCD(91, 49)

        Assertions.assertEquals(7, actual)
    }

    @Test
    fun calculateGcdTest2() {
        val actual = gcdCalculator.calculateGCD(49, 91)

        Assertions.assertEquals(7, actual)
    }
}