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
        //CAR tests
        "(car (cons 2 45)), 2",
        "(car (cons (car (cons 56 43)) T)), 56",
        //CDR tests
        "(cdr (cons 2 45)), 45",
        "(cdr (cons (cdr (cons 56 43)) T)), T",
        //CONS tests
        "(cons (+ 2 3)(cons 8 (null 5))), (5 8)",
        "(cons 2 (cons 3 (cons 4 5))), (2 3 4 . 5)",
        "(cons (cons 1 2) (cons 3 4)), ((1 . 2) 3 . 4)",
        "(cons (= 1 2) (= 3 3)), (NIL . T)",
        "(cons (car ('(7 8))) (cdr ('(6 10)))), (7 10)",
        //EQ tests
        "(= 23 23), T",
        "(= (= 34 34) (= (= 23 23) NIL)), NIL",
        "(= T T), T",
        "(= 2 (+ 1 1)), T",
        "(= (' XYZ1) (' XYZ1)), T",
        "(= (' A) (' B)), NIL",
        "(= (> 34 2) T), T",
        "(= (int NIL) (null T)), T",
        "(= (cons 23 ()) 9), NIL",
        //GREATER tests
        "(> 98 1), T",
        "(> (+ 3 2) 6), NIL",
        "(> (cdr (cons (+ 12 12) 6)) (- 13 19)), T",
        //INT tests
        "(int 3), T",
        "(int (int 45)), NIL",
        "(int (+ 4 5)), T",
        "(int (' X23)), NIL",
        "(int (= 34 34)), NIL",
        "(int (cons 4 5)), NIL",
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
        "(null (int (int (+ 23 23)))), T",
        //PLUS tests
        "(+ 8 4), 12",
        "(+ (+ 4 3) (+ (+ 1 2) 4)), 14",
        "(+ 1 2), 3",
        "(+ (+ 3 5) (* 4 4)), 24",
        "(+ (car (cons 2 0)) (car (cons 9 3))), 11",
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
        //CDR tests
        "(cdr), Error! Expected length of cdr list is 2!    Actual: 1",
        "(cdr 1 NIL T), Error! Expected length of cdr list is 2!    Actual: 4",
        "(cdr (int 2)), Error! Parameter of cdr is not a list.    Actual: T",
        //CONS tests
        "(cons), Error! Expected length of cons list is 3!    Actual: 1",
        "(cons 23 12 34), Error! Expected length of cons list is 3!    Actual: 4",
        //EQ tests
        "(=), Error! Expected length of = list is 3!    Actual: 1",
        "(= 23 T (CONS 23 1)), Error! Expected length of = list is 3!    Actual: 4",
        //GREATER tests
        "(>), Error! Expected length of > list is 3!    Actual: 1",
        "(> 23 45 98 34), Error! Expected length of > list is 3!    Actual: 5",
        "(> NIL 23), Error! Parameter at position: 1 of function > is not numeric!    Actual: NIL",
        //INT tests
        "(int), Error! Expected length of int list is 2!    Actual: 1",
        "(int 12 5 94 95), Error! Expected length of int list is 2!    Actual: 5",
        //LESS tests
        "(<), Error! Expected length of < list is 3!    Actual: 1",
        "(< 23 45 (cons T 45) 34), Error! Expected length of < list is 3!    Actual: 5",
        "(< () 23), Error! Parameter at position: 1 of function < is not numeric!    Actual: NIL",
        //MINUS tests
        "(-), Error! Expected length of - list is 3!    Actual: 1",
        "(- 22 (cons T 45) 34), Error! Expected length of - list is 3!    Actual: 4",
        "(- (cons 34 20) 23), Error! Parameter at position: 1 of function - is not numeric!    Actual: (34 . 20)",
        //NULL tests
        "(null), Error! Expected length of null list is 2!    Actual: 1",
        "(null 23 23 T), Error! Expected length of null list is 2!    Actual: 4",
        //PLUS tests
        "(+), Error! Expected length of + list is 3!    Actual: 1",
        "(+ T NIL 34), Error! Expected length of + list is 3!    Actual: 4",
        "(+ 23 (cons 34 20)), Error! Parameter at position: 2 of function + is not numeric!    Actual: (34 . 20)",
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