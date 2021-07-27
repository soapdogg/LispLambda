package lambda.singleton

import lambda.evaluator.rootnode.*
import lambda.evaluator.rootnode.internal.BuiltInFunctionEvaluator
import lambda.evaluator.rootnode.internal.FinishedProgramStackItemEvaluator
import lambda.evaluator.rootnode.internal.*

enum class RootNodeEvaluatorSingleton {
    INSTANCE;

    private val programStackItemGenerator = ProgramStackItemGenerator()
    private val topProgramStackItemCreator = TopProgramStackItemCreator(
        programStackItemGenerator
    )

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        programStackItemGenerator
    )

    private val postEvaluationStackUpdater = PostEvaluationStackUpdater(
        topProgramStackItemUpdater
    )

    private val builtInFunctionEvaluator = BuiltInFunctionEvaluator(
        FunctionSingleton.INSTANCE.functionMap,
        postEvaluationStackUpdater
    )

    private val nodeGenerator = NodeGenerator()

    private val condChildStackItemBuilder = CondChildStackItemBuilder(
        nodeGenerator,
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

    val rootNodeEvaluator = RootNodeEvaluator(
        topProgramStackItemCreator,
        condProgramStackItemEvaluator,
        condChildFunctionEvaluator,
        quoteFunctionEvaluator,
        unfinishedProgramStackItemEvaluator,
        finishedProgramStackItemEvaluator
    )
}