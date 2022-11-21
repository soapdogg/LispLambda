package lambda.evaluator

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class QuoteFunctionEvaluatorTest {

    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)

    private val quoteFunctionEvaluator = QuoteFunctionEvaluatorImpl(postEvaluationStackUpdater)

    @Test
    fun evaluateQuoteFunctionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val quoteExprNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(quoteExprNode)

        val child0 = Mockito.mock(Node::class.java)
        val quoted = Mockito.mock(Node::class.java)
        val quoteExprNodeChildren = listOf(child0, quoted)
        Mockito.`when`(quoteExprNode.children).thenReturn(quoteExprNodeChildren)

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        quoteFunctionEvaluator.evaluateQuoteFunction(
            top,
            evalStack,
            programStack
        )

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            quoted,
            variableMap,
            evalStack,
            programStack
        )
    }
}