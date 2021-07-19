package regression

import lambda.singleton.InterpreterSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.provider.CsvSource

class InterpreterV2Test {
    private val interpreter = InterpreterSingleton.INSTANCE.interpreter

    @ParameterizedTest
    @CsvSource(
        //EQ tests
        "(= 23 23), T",
        "(= (= 34 34) (= (= 23 23) NIL)), NIL",
        "(= T T), T",
        "(= 2 (+ 1 1)), T",
        "(= (' XYZ1) (' XYZ1)), T",
        "(= (' A) (' B)), NIL",
        "(= (> 34 2) T), T",
        //GREATER tests
        "(> 98 1), T",
        "(> (+ 3 2) 6), NIL",
        //LESS tests
        "(< 1 19), T",
        "(< (- 34 3) 1), NIL",
        //MINUS tests
        "(- 1 13), -12",
        "(- 13 (- (- 30 23) 7)), 13",
        //PLUS tests
        "(+ 8 4), 12",
        "(+ (+ 4 3) (+ (+ 1 2) 4)), 14",
        "(+ 1 2), 3",
        "(+ (+ 3 5) (* 4 4)), 24",
        //QUOTE tests
        "('(3 4 5)), (3 4 5)",
        //TIMES tests
        "(* (- 0 1) 45), -45",
        "(* 3 34), 102",
        "(* (* 1 2) (* 2 (* 5 6))), 120"
    )
    fun test(arguments: ArgumentsAccessor) {
        val input = arguments.get(0).toString()
        val expected = arguments.get(1)
        val actual = interpreter.interpret(input).trim()

        Assertions.assertEquals(expected, actual)
    }
}