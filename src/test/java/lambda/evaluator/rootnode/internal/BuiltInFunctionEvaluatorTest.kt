package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.function.Function
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class BuiltInFunctionEvaluatorTest {

    private val functionName = "functionName"
    private val function = Mockito.mock(Function::class.java)
    private val functionMap = mapOf(
        Pair(functionName, function)
    )
    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)

    private val builtInFunctionEvaluator = BuiltInFunctionEvaluator(
        functionMap,
        postEvaluationStackUpdater
    )

    @Test
    fun evaluateBuiltInFunctionTest() {
        val functionStack = Stack<Node>()
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val evaluatedFunctionResult = Mockito.mock(Node::class.java)
        Mockito.`when`(function.evaluate(functionStack)).thenReturn(evaluatedFunctionResult)

        builtInFunctionEvaluator.evaluateBuiltInFunction(
            functionName,
            functionStack,
            top,
            evalStack,
            programStack
        )

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            evaluatedFunctionResult,
            variableMap,
            evalStack,
            programStack
        )
    }
}