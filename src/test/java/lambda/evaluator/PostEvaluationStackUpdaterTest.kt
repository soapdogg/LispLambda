package lambda.evaluator

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class PostEvaluationStackUpdaterTest {

    private val topProgramStackItemUpdater = Mockito.mock(TopProgramStackItemUpdater::class.java)

    private val postEvaluationStackUpdater = PostEvaluationStackUpdaterImpl(
        topProgramStackItemUpdater
    )

    @Test
    fun updateStacksAfterEvaluationAtomNodeTest() {
        val evaluatedNode = Mockito.mock(AtomNode::class.java)
        val variableMap = emptyMap<String, Node>()
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val value = "value"
        Mockito.`when`(evaluatedNode.value).thenReturn(value)

        postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedNode,
            variableMap,
            evalStack,
            programStack
        )

        Assertions.assertEquals(evaluatedNode, evalStack.peek())
        Mockito.verify(
            topProgramStackItemUpdater
        ).updateTopProgramStackItemToNextChild(programStack)
    }

    @Test
    fun updateStacksAfterEvaluationTest() {
        val evaluatedNode = Mockito.mock(Node::class.java)
        val variableMap = emptyMap<String, Node>()
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedNode,
            variableMap,
            evalStack,
            programStack
        )

        Assertions.assertEquals(evaluatedNode, evalStack.peek())
        Mockito.verify(
            topProgramStackItemUpdater
        ).updateTopProgramStackItemToNextChild(programStack)
    }
}