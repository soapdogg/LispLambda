package lambda.evaluator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StackGeneratorTest {

    private val stackGenerator = StackGenerator.newInstance()

    @Test
    fun generateNewStackTest() {
        val stack = stackGenerator.generateNewStack(Int::class.java)

        Assertions.assertTrue(stack.isEmpty())
    }
}