package regression

import lambda.singleton.InterpreterSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import regression.ScannerUtils.getScannerFromFilePath
import regression.ScannerUtils.scanExpected
import regression.ScannerUtils.transformScannerInputToLispInput

class InterpreterTest {
    //ATOM TESTS
    @Test
    fun project3AtomValidTest() {
        interpreterTest("data/input/project3/atom/valid.lisp", "data/expected/project3/atom/valid.txt")
    }

    @Test
    fun project3AtomInvalid1Test() {
        interpreterTest("data/input/project3/atom/invalid1.lisp", "data/expected/project3/atom/invalid1.txt")
    }

    @Test
    fun project3AtomInvalid2Test() {
        interpreterTest("data/input/project3/atom/invalid2.lisp", "data/expected/project3/atom/invalid2.txt")
    }

    //ATOMIC TESTS
    @Test
    fun project3AtomicValidTest() {
        interpreterTest("data/input/project3/atomic/valid.lisp", "data/expected/project3/atomic/valid.txt")
    }

    @Test
    fun project3AtomicInvalid1Test() {
        interpreterTest("data/input/project3/atomic/invalid1.lisp", "data/expected/project3/atomic/invalid1.txt")
    }

    @Test
    fun project3AtomicInvalid2Test() {
        interpreterTest("data/input/project3/atomic/invalid2.lisp", "data/expected/project3/atomic/invalid2.txt")
    }

    @Test
    fun project3AtomicInvalid3Test() {
        interpreterTest("data/input/project3/atomic/invalid3.lisp", "data/expected/project3/atomic/invalid3.txt")
    }

    @Test
    fun project3AtomicInvalid4Test() {
        interpreterTest("data/input/project3/atomic/invalid4.lisp", "data/expected/project3/atomic/invalid4.txt")
    }

    //CAR TESTS
    @Test
    fun project3CarValidTest() {
        interpreterTest("data/input/project3/car/valid.lisp", "data/expected/project3/car/valid.txt")
    }

    @Test
    fun project3CarInvalid1Test() {
        interpreterTest("data/input/project3/car/invalid1.lisp", "data/expected/project3/car/invalid1.txt")
    }

    @Test
    fun project3CarInvalid2Test() {
        interpreterTest("data/input/project3/car/invalid2.lisp", "data/expected/project3/car/invalid2.txt")
    }

    @Test
    fun project3CarInvalid3Test() {
        interpreterTest("data/input/project3/car/invalid3.lisp", "data/expected/project3/car/invalid3.txt")
    }

    @Test
    fun project3CarInvalid4Test() {
        interpreterTest("data/input/project3/car/invalid4.lisp", "data/expected/project3/car/invalid4.txt")
    }

    //CDR TESTS
    @Test
    fun project3CdrValidTest() {
        interpreterTest("data/input/project3/cdr/valid.lisp", "data/expected/project3/cdr/valid.txt")
    }

    @Test
    fun project3CdrInvalid1Test() {
        interpreterTest("data/input/project3/cdr/invalid1.lisp", "data/expected/project3/cdr/invalid1.txt")
    }

    @Test
    fun project3CdrInvalid2Test() {
        interpreterTest("data/input/project3/cdr/invalid2.lisp", "data/expected/project3/cdr/invalid2.txt")
    }

    @Test
    fun project3CdrInvalid3Test() {
        interpreterTest("data/input/project3/cdr/invalid3.lisp", "data/expected/project3/cdr/invalid3.txt")
    }

    //COND TESTS
    @Test
    fun project3CondValidTest() {
        interpreterTest("data/input/project3/cond/valid.lisp", "data/expected/project3/cond/valid.txt")
    }

    @Test
    fun project3CondInvalid1Test() {
        interpreterTest("data/input/project3/cond/invalid1.lisp", "data/expected/project3/cond/invalid1.txt")
    }

    @Test
    fun project3CondInvalid2Test() {
        interpreterTest("data/input/project3/cond/invalid2.lisp", "data/expected/project3/cond/invalid2.txt")
    }

    @Test
    fun project3CondInvalid3Test() {
        interpreterTest("data/input/project3/cond/invalid3.lisp", "data/expected/project3/cond/invalid3.txt")
    }

    @Test
    fun project3CondInvalid4Test() {
        interpreterTest("data/input/project3/cond/invalid4.lisp", "data/expected/project3/cond/invalid4.txt")
    }

    //CONS TESTS
    @Test
    fun project3ConsValidTest() {
        interpreterTest("data/input/project3/cons/valid.lisp", "data/expected/project3/cons/valid.txt")
    }

    @Test
    fun project3ConsInvalid1Test() {
        interpreterTest("data/input/project3/cons/invalid1.lisp", "data/expected/project3/cons/invalid1.txt")
    }

    @Test
    fun project3ConsInvalid2Test() {
        interpreterTest("data/input/project3/cons/invalid2.lisp", "data/expected/project3/cons/invalid2.txt")
    }

    //EQ TESTS
    @Test
    fun project3EqValidTest() {
        interpreterTest("data/input/project3/eq/valid.lisp", "data/expected/project3/eq/valid.txt")
    }

    @Test
    fun project3EqInvalid3Test() {
        interpreterTest("data/input/project3/eq/invalid3.lisp", "data/expected/project3/eq/invalid3.txt")
    }

    //INT TESTS
    @Test
    fun project3IntValidTest() {
        interpreterTest("data/input/project3/int/valid.lisp", "data/expected/project3/int/valid.txt")
    }

    @Test
    fun project3IntInvalid1Test() {
        interpreterTest("data/input/project3/int/invalid1.lisp", "data/expected/project3/int/invalid1.txt")
    }

    @Test
    fun project3IntInvalid2Test() {
        interpreterTest("data/input/project3/int/invalid2.lisp", "data/expected/project3/int/invalid2.txt")
    }

    //LESS TESTS
    @Test
    fun project3LessValidTest() {
        interpreterTest("data/input/project3/less/valid.lisp", "data/expected/project3/less/valid.txt")
    }

    //NULL TESTS
    @Test
    fun project3NullValidTest() {
        interpreterTest("data/input/project3/null/valid.lisp", "data/expected/project3/null/valid.txt")
    }

    //PLUS TESTS
    @Test
    fun project3PlusValidTest() {
        interpreterTest("data/input/project3/plus/valid.lisp", "data/expected/project3/plus/valid.txt")
    }

    //QUOTE TESTS
    @Test
    fun project3QuoteValidTest() {
        interpreterTest("data/input/project3/quote/valid.lisp", "data/expected/project3/quote/valid.txt")
    }

    //TIMES TESTS
    @Test
    fun project3TimesValidTest() {
        interpreterTest("data/input/project3/times/valid.lisp", "data/expected/project3/times/valid.txt")
    }

    companion object {
        private fun interpreterTest(
            programFile: String,
            expectedFile: String
        ) {
            val interpreter = InterpreterSingleton.INSTANCE.interpreter
            val actual = try {
                val `in` = getScannerFromFilePath(programFile)
                val input = transformScannerInputToLispInput(`in`)
                interpreter.interpret(input)
            } catch (e: Exception) {
                e.message.toString()
            }
            val expected = scanExpected(expectedFile)
            Assertions.assertEquals(expected, actual)
        }
    }
}