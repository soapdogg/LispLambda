package lambda.interpreter

import lambda.asserter.ExpressionListLengthAsserter
import lambda.core.datamodels.*
import lambda.evaluator.ProgramEvaluator
import lambda.userdefined.UserDefinedFunctionGenerator
import lambda.parser.Parser
import lambda.printer.ListNotationPrinter
import lambda.tokenizer.Tokenizer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class InterpreterTest {
    private val input = "input"
    private val tokenizer: Tokenizer = Mockito.mock(Tokenizer::class.java)
    private val parser: Parser = Mockito.mock(Parser::class.java)
    private val program: ProgramEvaluator = Mockito.mock(ProgramEvaluator::class.java)
    private val rootNodePartitioner: RootNodePartitioner = Mockito.mock(RootNodePartitioner::class.java)
    private val defunFunction = Mockito.mock(UserDefinedFunctionGenerator::class.java)
    private val expressionListLengthAsserter = Mockito.mock(ExpressionListLengthAsserter::class.java)
    private val listNotationPrinter: ListNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val interpreter: Interpreter = Interpreter(
        tokenizer,
        parser,
        rootNodePartitioner,
        defunFunction,
        expressionListLengthAsserter,
        program,
        listNotationPrinter
    )

    @Test
    fun interpretTest() {
        val tokens: List<String> = listOf()
        Mockito.`when`(tokenizer.tokenize(input)).thenReturn(tokens)

        val rootNodes= listOf<Node>()
        Mockito.`when`(parser.parse(tokens)).thenReturn(rootNodes)

        val partitionedRootNodes = Mockito.mock(PartitionedRootNodes::class.java)
        Mockito.`when`(rootNodePartitioner.partitionRootNodes(rootNodes)).thenReturn(partitionedRootNodes)

        val defunNode = Mockito.mock(ExpressionListNode::class.java)
        val defunNodes: List<ExpressionListNode> = listOf(defunNode)
        Mockito.`when`(partitionedRootNodes.defunNodes).thenReturn(defunNodes)

        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
        val functionName = "functionName"
        val pair = Pair(functionName, userDefinedFunction)
        Mockito.`when`(defunFunction.generateUserDefinedFunction(defunNode)).thenReturn(pair)

        val node = Mockito.mock(Node::class.java)
        val evaluatableNodes: List<Node> = listOf(node)
        Mockito.`when`(partitionedRootNodes.evaluatableNodes).thenReturn(evaluatableNodes)

        val evaluatedNode = Mockito.mock(Node::class.java)
        val evaluatedNodes = listOf(evaluatedNode)
        val userDefinedFunctions: Map<String, UserDefinedFunction> = mapOf(pair)

        Mockito.`when`(
            program.evaluate(
                evaluatableNodes,
                userDefinedFunctions
            )
        ).thenReturn(evaluatedNodes)


        val value = "value"
        Mockito.`when`(listNotationPrinter.printInListNotation(evaluatedNodes)).thenReturn(value)

        val actual = interpreter.interpret(input)
        Assertions.assertEquals(value, actual)
    }
}