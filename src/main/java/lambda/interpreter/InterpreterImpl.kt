package lambda.interpreter

import lambda.Interpreter
import lambda.ListNotationPrinter
import lambda.Parser
import lambda.ProgramEvaluator
import lambda.Tokenizer
import lambda.UserDefinedFunctionGenerator

internal class InterpreterImpl(
    private val tokenizer: Tokenizer,
    private val parser: Parser,
    private val rootNodePartitioner: RootNodePartitioner,
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
    private val expressionListLengthAsserter: ExpressionListLengthAsserter,
    private val programEvaluator: ProgramEvaluator,
    private val listNotationPrinter: ListNotationPrinter
): Interpreter {

    override fun interpret(
        input :String
    ): String {
        val tokens = tokenizer.tokenize(input)
        val rootNodes = parser.parse(tokens)

        val partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        val userDefinedFunctions = partitionedRootNodes.defunNodes.associate {
            userDefinedFunctionGenerator.generateUserDefinedFunction(it)
        }

        expressionListLengthAsserter.assertLengthIsAsExpected(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        val evaluatedNodes = programEvaluator.evaluate(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        return listNotationPrinter.printInListNotation(evaluatedNodes)
    }
}