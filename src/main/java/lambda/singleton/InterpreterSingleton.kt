package lambda.singleton

import lambda.interpreter.Interpreter
import lambda.interpreter.RootNodePartitioner

enum class InterpreterSingleton {
    INSTANCE;

    val rootNodePartitioner: RootNodePartitioner = RootNodePartitioner()
    val interpreter: Interpreter = Interpreter(
        TokenizerSingleton.INSTANCE.tokenizer,
        ParserSingleton.INSTANCE.parser,
        rootNodePartitioner,
        UserDefinedSingleton.INSTANCE.userDefinedFunctionGenerator,
        AsserterSingleton.INSTANCE.expressionListLengthAsserter,
        EvaluatorSingleton.INSTANCE.programEvaluator,
        PrinterSingleton.INSTANCE.listNotationPrinter
    )

}