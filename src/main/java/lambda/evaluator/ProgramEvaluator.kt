package lambda.evaluator

import lambda.ProgramEvaluator
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.core.datamodels.UserDefinedFunction

internal class ProgramEvaluatorImpl(
    private val atomRootNodeAsserter: AtomRootNodeAsserter,
    private val rootNodeEvaluator: RootNodeEvaluator,
    private val stackGenerator: StackGenerator
): ProgramEvaluator {
    override fun evaluate(
        rootNodes: List<Node>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): List<Node> {
        return rootNodes.map {
            if (it is AtomNode) {
                atomRootNodeAsserter.assertAtomRootNode(it)
                return@map it
            }
            val programStack = stackGenerator.generateNewStack(ProgramStackItem::class.java)
            val evaluationStack = stackGenerator.generateNewStack(Node::class.java)
            rootNodeEvaluator.evaluate(
                it as ExpressionListNode,
                userDefinedFunctions,
                programStack,
                evaluationStack
            )
        }
    }
}