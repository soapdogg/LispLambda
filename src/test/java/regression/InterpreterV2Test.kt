package regression

import lambda.singleton.InterpreterSingleton
import org.junit.jupiter.api.Assertions
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
        //NULL tests
        "(null NIL), T",
        "(null (null NIL)), NIL",
        "(null ('(A))), NIL",
        "(null (= 2 (+ 1 1))), NIL",
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
    fun validTest(arguments: ArgumentsAccessor) {
        val input = arguments.get(0).toString()
        val expected = arguments.get(1)
        val actual = interpreter.interpret(input).trim()

        Assertions.assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        //CAR tests
        "(car), Error! Expected length of car list is 2!    Actual: 1",
        "(car 23 T), Error! Expected length of car list is 2!    Actual: 3",
        "(car 23), Error! Parameter of car is not a list.    Actual: 23",
        "(car (1 2 3)), Error! Invalid CAR value: 1",
        //EQ tests
        "(=), Error! Expected length of = list is 3!    Actual: 1",
        "(= 23 T (CONS 23 1)), Error! Expected length of = list is 3!    Actual: 4",
        //GREATER tests
        "(>), Error! Expected length of > list is 3!    Actual: 1",
        "(> 23 45 98 34), Error! Expected length of > list is 3!    Actual: 5",
        "(> NIL 23), Error! Parameter at position: 1 of function > is not numeric!    Actual: NIL",
        //LESS tests
        "(<), Error! Expected length of < list is 3!    Actual: 1",
        "(< 23 45 (CONS T 45) 34), Error! Expected length of < list is 3!    Actual: 5",
        "(< () 23), Error! Parameter at position: 1 of function < is not numeric!    Actual: NIL",
        //MINUS tests
        "(-), Error! Expected length of - list is 3!    Actual: 1",
        "(- 22 (CONS T 45) 34), Error! Expected length of - list is 3!    Actual: 4",
        "(- (CONS 34 20) 23), Error! Parameter at position: 1 of function - is not numeric!    Actual: (34 . 20)",
        //NULL tests
        "(null), Error! Expected length of null list is 2!    Actual: 1",
        "(null 23 23 T), Error! Expected length of null list is 2!    Actual: 4",
        //PLUS tests
        "(+), Error! Expected length of + list is 3!    Actual: 1",
        "(+ T NIL 34), Error! Expected length of + list is 3!    Actual: 4",
        "(+ 23 (CONS 34 20)), Error! Parameter at position: 2 of function + is not numeric!    Actual: (34 . 20)",
        //QUOTE tests
        "('), Error! Expected length of ' list is 2!    Actual: 1",
        "(' T NIL), Error! Expected length of ' list is 2!    Actual: 3",
        //TIMES tests
        "(*), Error! Expected length of * list is 3!    Actual: 1",
        "(* 23 4 1), Error! Expected length of * list is 3!    Actual: 4",
        "(* 2 T), Error! Parameter at position: 2 of function * is not numeric!    Actual: T"
    )
    fun invalidTest(arguments: ArgumentsAccessor) {
        val input = arguments.get(0).toString()
        val expected = arguments.get(1)

        try {
            interpreter.interpret(input)
            Assertions.fail()
        } catch(e: Exception) {
            val actual = e.message.toString().trim()
            Assertions.assertEquals(expected, actual)
        }
    }
}