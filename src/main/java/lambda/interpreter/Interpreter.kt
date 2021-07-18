package lambda.interpreter

import lambda.asserter.ExpressionListLengthAsserter
import lambda.evaluator.ProgramEvaluator
import lambda.generator.UserDefinedFunctionGenerator
import lambda.parser.RootParser
import lambda.printer.ListNotationPrinter
import lambda.tokenizer.Tokenizer
import java.util.Scanner

class Interpreter(
    private val tokenizer: Tokenizer,
    private val rootParser: RootParser,
    private val rootNodePartitioner: RootNodePartitioner,
    private val userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
    private val expressionListLengthAsserter: ExpressionListLengthAsserter,
    private val programEvaluator: ProgramEvaluator,
    private val listNotationPrinter: ListNotationPrinter
){

    fun interpret(
        scanner: Scanner
    ): String {
        val tokens = tokenizer.tokenize(scanner)
        val rootNodes = rootParser.parse(tokens)

        val partitionedRootNodes = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        val userDefinedFunctions = partitionedRootNodes.defunNodes.map{
            userDefinedFunctionGenerator.evaluateLispFunction(it) }.toMap()

        expressionListLengthAsserter.assertLengthIsAsExpected(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        val evaluatedNodes = programEvaluator.evaluatePostOrder(
            partitionedRootNodes.evaluatableNodes,
            userDefinedFunctions
        )

        return listNotationPrinter.printInListNotation(evaluatedNodes)
    }
}