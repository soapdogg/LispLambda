package lambda.evaluator

import lambda.evaluator.internal.AtomRootNodeAsserter
import lambda.core.datamodels.*
import lambda.evaluator.internal.StackGenerator
import lambda.evaluator.rootnode.RootNodeEvaluator

class ProgramEvaluator(
    private val atomRootNodeAsserter: AtomRootNodeAsserter,
    private val rootNodeEvaluator: RootNodeEvaluator,
    private val stackGenerator: StackGenerator
) {
    fun evaluate(
        rootNodes: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): List<NodeV2> {
        return rootNodes.map {
            if (it is AtomNode) {
                atomRootNodeAsserter.assertAtomRootNode(it)
                return@map it
            }
            val programStack = stackGenerator.generateNewStack(ProgramStackItem::class.java)
            val evaluationStack = stackGenerator.generateNewStack(NodeV2::class.java)
            rootNodeEvaluator.evaluate(
                it as ExpressionListNode,
                userDefinedFunctions,
                programStack,
                evaluationStack
            )
        }
    }
}