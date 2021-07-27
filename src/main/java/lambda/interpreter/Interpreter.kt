package lambda.interpreter

import lambda.asserter.ExpressionListLengthAsserter
import lambda.evaluator.ProgramEvaluator
import lambda.userdefined.UserDefinedFunctionGenerator
import lambda.parser.Parser
import lambda.printer.ListNotationPrinter
import lambda.tokenizer.Tokenizer

class Interpreter(
    private val tokenizer: Tokenizer,
    private val parser: Parser,
    private val rootNodePartitioner: RootNodePartitioner,
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
    private val expressionListLengthAsserter: ExpressionListLengthAsserter,
    private val programEvaluator: ProgramEvaluator,
    private val listNotationPrinter: ListNotationPrinter
){

    fun interpret(
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