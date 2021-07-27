package lambda.singleton

import lambda.evaluator.*
import lambda.evaluator.internal.AtomRootNodeAsserter
import lambda.evaluator.internal.StackGenerator

enum class EvaluatorSingleton {
    INSTANCE;

    private val stackGenerator = StackGenerator()

    private val atomRootNodeAsserter = AtomRootNodeAsserter()

    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        atomRootNodeAsserter,
        RootNodeEvaluatorSingleton.INSTANCE.rootNodeEvaluator,
        stackGenerator
    )
}