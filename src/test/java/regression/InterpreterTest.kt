package regression

import lambda.singleton.InterpreterSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import regression.ScannerUtils.getScannerFromFilePath
import regression.ScannerUtils.scanExpected
import regression.ScannerUtils.transformScannerInputToLispInput

class InterpreterTest {
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