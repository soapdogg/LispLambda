package regression

import lambda.singleton.ParserSingleton
import lambda.singleton.PrinterSingleton
import lambda.singleton.TokenizerSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import regression.ScannerUtils.getScannerFromFilePath
import regression.ScannerUtils.scanExpected

class ParserTest {
    @Test
    fun project2ValidTest() {
        parserTest("data/input/project2/valid.lisp", "data/expected/project2/valid.txt")
    }

    @Test
    fun project2Valid2Test() {
        parserTest("data/input/project2/valid2.lisp", "data/expected/project2/valid2.txt")
    }

    @Test
    fun project2Valid3Test() {
        parserTest("data/input/project2/valid3.lisp", "data/expected/project2/valid3.txt")
    }

    @Test
    fun project2Valid4Test() {
        parserTest("data/input/project2/valid4.lisp", "data/expected/project2/valid4.txt")
    }

    @Test
    fun project2Valid5Test() {
        parserTest("data/input/project2/valid5.lisp", "data/expected/project2/valid5.txt")
    }

    @Test
    fun project2Valid6Test() {
        parserTest("data/input/project2/valid6.lisp", "data/expected/project2/valid6.txt")
    }

    @Test
    fun project2Valid7Test() {
        parserTest("data/input/project2/valid7.lisp", "data/expected/project2/valid7.txt")
    }

    @Test
    fun project2Invalid1Test() {
        parserTest("data/input/project2/invalid1.lisp", "data/expected/project2/invalid1.txt")
    }

    @Test
    fun project2Invalid2Test() {
        parserTest("data/input/project2/invalid2.lisp", "data/expected/project2/invalid2.txt")
    }

    @Test
    fun project2Invalid3Test() {
        parserTest("data/input/project2/invalid3.lisp", "data/expected/project2/invalid3.txt")
    }

    @Test
    fun project2Invalid4Test() {
        parserTest("data/input/project2/invalid4.lisp", "data/expected/project2/invalid4.txt")
    }

    @Test
    fun project2Invalid5Test() {
        parserTest("data/input/project2/invalid5.lisp", "data/expected/project2/invalid5.txt")
    }

    companion object {
        private fun parserTest(programFile: String, expectedFile: String) {
            val tokenizer = TokenizerSingleton.INSTANCE.tokenizer
            val rootParser = ParserSingleton.INSTANCE.rootParser
            val dotNotationPrinter = PrinterSingleton.INSTANCE.dotNotationPrinter
            val actual = try {
                val `in` = getScannerFromFilePath(programFile)
                val input = ScannerUtils.transformScannerInputToLispInput(`in`)
                val tokens = tokenizer.tokenize(input)
                val nodes = rootParser.parse(tokens)
                dotNotationPrinter.printInDotNotation(nodes)
            } catch (e: Exception) {
                e.message
            }
            val expected = scanExpected(expectedFile)
            Assertions.assertEquals(expected, actual)
        }
    }
}