package lambda.interpreter

import lambda.FunctionLengthAsserter
import lambda.Interpreter
import lambda.ListNotationPrinter
import lambda.Parser
import lambda.ProgramEvaluator
import lambda.Tokenizer
import lambda.UserDefinedFunctionGenerator
import lambda.asserter.AsserterSingleton
import lambda.core.constants.FunctionLengthConstants
import lambda.core.constants.FunctionNameConstants
import lambda.evaluator.EvaluatorSingleton
import lambda.function.FunctionSingleton
import lambda.parser.ParserSingleton
import lambda.printer.PrinterSingleton
import lambda.tokenizer.TokenizerSingleton
import lambda.userdefined.UserDefinedSingleton

enum class InterpreterSingleton {
    INSTANCE;
    fun getInterpreter(
        tokenizer: Tokenizer,
        parser: Parser,
        functionLengthAsserter: FunctionLengthAsserter,
        userDefinedFunctionGenerator: UserDefinedFunctionGenerator,
        programEvaluator: ProgramEvaluator,
        listNotationPrinter: ListNotationPrinter
    ): Interpreter {
        val rootNodePartitioner: RootNodePartitioner = RootNodePartitionerImpl()

        val functionLengthMap = mapOf(
            Pair(FunctionNameConstants.ATOM, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.CAR, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.CDR, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.CONS, FunctionLengthConstants.THREE),
            Pair(FunctionNameConstants.EVEN_P, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.EXP, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.EXPT, FunctionLengthConstants.THREE),
            Pair(FunctionNameConstants.INT, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.MINUS_P, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.NULL, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.ODD_P, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.ONE_MINUS, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.ONE_PLUS, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.PLUS_P, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.QUOTE, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.ZERO_P, FunctionLengthConstants.TWO)
        )

        val minimumFunctionLengthMap = mapOf(
            Pair(FunctionNameConstants.EQ, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.GREATER, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.GREATER_EQ, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.LCM, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.LESS, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.LESS_EQ, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.MAX, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.MIN, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.MINUS, FunctionLengthConstants.TWO),
            Pair(FunctionNameConstants.NOT_EQ, FunctionLengthConstants.TWO)
        )

        val expressionListLengthAsserter = ExpressionListLengthAsserterImpl(
            functionLengthAsserter,
            functionLengthMap,
            minimumFunctionLengthMap
        )
        return InterpreterImpl(
            tokenizer,
            parser,
            rootNodePartitioner,
            userDefinedFunctionGenerator,
            expressionListLengthAsserter,
            programEvaluator,
            listNotationPrinter
        )
    }
}