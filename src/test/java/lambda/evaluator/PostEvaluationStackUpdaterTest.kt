package lambda.evaluator

import lambda.datamodels.AtomNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.datamodels.ProgramStackItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class PostEvaluationStackUpdaterTest {

    private val topProgramStackItemUpdater = Mockito.mock(TopProgramStackItemUpdater::class.java)

    private val postEvaluationStackUpdater = PostEvaluationStackUpdater(
        topProgramStackItemUpdater
    )

    @Test
    fun updateStacksAfterEvaluationAtomNodeTest() {
        val evaluatedNode = Mockito.mock(AtomNode::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val evalStack = Stack<NodeV2>()
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
        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val evalStack = Stack<NodeV2>()
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