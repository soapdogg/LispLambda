package lambda.singleton

import lambda.evaluator.*
import lambda.generator.StackGenerator

enum class EvaluatorSingleton {
    INSTANCE;

    private val topProgramStackItemCreator = TopProgramStackItemCreator(
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val postEvaluationStackUpdater = PostEvaluationStackUpdater(
        topProgramStackItemUpdater
    )

    private val builtInFunctionEvaluator = BuiltInFunctionEvaluator(
        FunctionSingleton.INSTANCE.functionMap,
        postEvaluationStackUpdater
    )

    private val condChildStackItemBuilder = CondChildStackItemBuilder(
        GeneratorSingleton.INSTANCE.nodeGenerator,
        topProgramStackItemCreator
    )

    private val condProgramStackItemEvaluator = CondFunctionEvaluator(
        topProgramStackItemUpdater,
        condChildStackItemBuilder
    )

    private val quoteFunctionEvaluator = QuoteFunctionEvaluator(
        postEvaluationStackUpdater
    )

    private val stackUpdateDeterminer = StackUpdateDeterminer(
        topProgramStackItemCreator,
        postEvaluationStackUpdater
    )

    private val condChildFunctionEvaluator = CondChildFunctionEvaluator(
        stackUpdateDeterminer
    )

    private val userDefinedFunctionEvaluator = UserDefinedFunctionEvaluator(
        stackUpdateDeterminer
    )

    private val unfinishedProgramStackItemEvaluator = UnfinishedProgramStackItemEvaluator(
        stackUpdateDeterminer
    )

    private val finishedProgramStackItemEvaluator = FinishedProgramStackItemEvaluator(
        postEvaluationStackUpdater,
        FunctionSingleton.INSTANCE.functionMap,
        builtInFunctionEvaluator,
        userDefinedFunctionEvaluator
    )

    private val nodeEvaluatorIterative = RootNodeEvaluator(
        topProgramStackItemCreator,
        condProgramStackItemEvaluator,
        condChildFunctionEvaluator,
        quoteFunctionEvaluator,
        unfinishedProgramStackItemEvaluator,
        finishedProgramStackItemEvaluator
    )

    val stackGenerator = StackGenerator()

    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluatorIterative,
        stackGenerator
    )
}