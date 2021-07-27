package lambda.evaluator.internal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StackGeneratorTest {

    private val stackGenerator = StackGenerator()

    @Test
    fun generateNewStackTest() {
        val stack = stackGenerator.generateNewStack(Int::class.java)

        Assertions.assertTrue(stack.isEmpty())
    }
}