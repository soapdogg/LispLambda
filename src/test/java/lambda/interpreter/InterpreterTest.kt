package lambda.interpreter

import lambda.asserter.ExpressionListLengthAsserter
import lambda.datamodels.*
import lambda.evaluator.ProgramEvaluator
import lambda.generator.UserDefinedFunctionGenerator
import lambda.parser.RootParser
import lambda.printer.ListNotationPrinter
import lambda.tokenizer.Tokenizer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class InterpreterTest {
    private val lines = listOf<String>()
    private val tokenizer: Tokenizer = Mockito.mock(Tokenizer::class.java)
    private val rootParser: RootParser = Mockito.mock(RootParser::class.java)
    private val program: ProgramEvaluator = Mockito.mock(ProgramEvaluator::class.java)
    private val rootNodePartitioner: RootNodePartitioner = Mockito.mock(RootNodePartitioner::class.java)
    private val defunFunction = Mockito.mock(UserDefinedFunctionGenerator::class.java)
    private val expressionListLengthAsserter = Mockito.mock(ExpressionListLengthAsserter::class.java)
    private val listNotationPrinter: ListNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val interpreter: Interpreter = Interpreter(
        tokenizer,
        rootParser,
        rootNodePartitioner,
        defunFunction,
        expressionListLengthAsserter,
        program,
        listNotationPrinter
    )

    @Test
    fun interpretTest() {
        val tokens: List<Token> = listOf()
        Mockito.`when`(tokenizer.tokenize(lines)).thenReturn(tokens)

        val rootNodes= listOf<NodeV2>()
        Mockito.`when`(rootParser.parse(tokens)).thenReturn(rootNodes)

        val partitionedRootNodes = Mockito.mock(PartitionedRootNodes::class.java)
        Mockito.`when`(rootNodePartitioner.partitionRootNodes(rootNodes)).thenReturn(partitionedRootNodes)

        val defunNode = Mockito.mock(ExpressionListNode::class.java)
        val defunNodes: List<ExpressionListNode> = listOf(defunNode)
        Mockito.`when`(partitionedRootNodes.defunNodes).thenReturn(defunNodes)

        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
        val functionName = "functionName"
        val pair = Pair(functionName, userDefinedFunction)
        Mockito.`when`(defunFunction.evaluateLispFunction(defunNode)).thenReturn(pair)

        val node = Mockito.mock(NodeV2::class.java)
        val evaluatableNodes: List<NodeV2> = listOf(node)
        Mockito.`when`(partitionedRootNodes.evaluatableNodes).thenReturn(evaluatableNodes)

        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        val evaluatedNodes = listOf(evaluatedNode)
        val userDefinedFunctions: Map<String, UserDefinedFunction> = mapOf(pair)

        Mockito.`when`(
            program.evaluatePostOrder(
                evaluatableNodes,
                userDefinedFunctions
            )
        ).thenReturn(evaluatedNodes)


        val value = "value"
        Mockito.`when`(listNotationPrinter.printInListNotation(evaluatedNodes)).thenReturn(value)

        val actual = interpreter.interpret(lines)
        Assertions.assertEquals(value, actual)
    }
}