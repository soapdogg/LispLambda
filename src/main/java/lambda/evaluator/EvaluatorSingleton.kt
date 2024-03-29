package lambda.evaluator

import lambda.Function
import lambda.ProgramEvaluator

enum class EvaluatorSingleton {
    INSTANCE;

    fun getProgramEvaluator(
        functionMap: Map<String, Function>
    ): ProgramEvaluator {
        val stackGenerator = StackGenerator.newInstance()
        val programStackItemGenerator = ProgramStackItemGenerator.newInstance()
        val topProgramStackItemCreator = TopProgramStackItemCreator.newInstance(programStackItemGenerator)
        val topProgramStackItemUpdater = TopProgramStackItemUpdaterImpl(programStackItemGenerator)
        val postEvaluationStackUpdater = PostEvaluationStackUpdaterImpl(topProgramStackItemUpdater)
        val builtInFunctionEvaluator = BuiltInFunctionEvaluatorImpl(
            functionMap,
            postEvaluationStackUpdater
        )
        val nodeGenerator = NodeGenerator.newInstance()

        val condChildStackItemBuilder = CondChildStackItemBuilderImpl(
            nodeGenerator,
            topProgramStackItemCreator
        )

        val condProgramStackItemEvaluator = CondFunctionEvaluatorImpl(
            topProgramStackItemUpdater,
            condChildStackItemBuilder
        )

        val quoteFunctionEvaluator = QuoteFunctionEvaluator.newInstance(postEvaluationStackUpdater)

        val stackUpdateDeterminer = StackUpdateDeterminerImpl(
            topProgramStackItemCreator,
            postEvaluationStackUpdater
        )

        val condChildFunctionEvaluator = CondChildFunctionEvaluatorImpl(stackUpdateDeterminer)
        val userDefinedFunctionEvaluator = UserDefinedFunctionEvaluatorImpl(stackUpdateDeterminer)
        val unfinishedProgramStackItemEvaluator = UnfinishedProgramStackItemEvaluatorImpl(stackUpdateDeterminer)

        val finishedProgramStackItemEvaluator = FinishedProgramStackItemEvaluatorImpl(
            postEvaluationStackUpdater,
            functionMap,
            builtInFunctionEvaluator,
            userDefinedFunctionEvaluator
        )

        val rootNodeEvaluator = RootNodeEvaluatorImpl(
            topProgramStackItemCreator,
            condProgramStackItemEvaluator,
            condChildFunctionEvaluator,
            quoteFunctionEvaluator,
            unfinishedProgramStackItemEvaluator,
            finishedProgramStackItemEvaluator
        )

        val atomRootNodeAsserter = AtomRootNodeAsserterImpl()

        return ProgramEvaluatorImpl(
            atomRootNodeAsserter,
            rootNodeEvaluator,
            stackGenerator
        )
    }
}