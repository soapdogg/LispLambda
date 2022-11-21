package lambda.evaluator

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.core.datamodels.UserDefinedFunction
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserDefinedFunctionEvaluatorTest {

    private val stackUpdateDeterminer = Mockito.mock(StackUpdateDeterminer::class.java)

    private val userDefinedFunctionEvaluator = UserDefinedFunctionEvaluatorImpl(
        stackUpdateDeterminer
    )

    @Test
    fun evaluateUserDefinedFunctionTest() {
        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
        val variableMap = emptyMap<String, Node>()
        val functionStack = Stack<Node>()
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val formalParameter0 = "formalParameter0"
        Mockito.`when`(userDefinedFunction.formalParameters).thenReturn(listOf(formalParameter0))
        val paramValue = Mockito.mock(Node::class.java)

        val mapCopy = mapOf(
            Pair(formalParameter0, paramValue)
        )

        functionStack.push(paramValue)

        val userDefinedFunctionBody = Mockito.mock(Node::class.java)
        Mockito.`when`(userDefinedFunction.body).thenReturn(userDefinedFunctionBody)

        userDefinedFunctionEvaluator.evaluateUserDefinedFunction(
            userDefinedFunction,
            variableMap,
            functionStack,
            evalStack,
            programStack
        )

        Mockito.verify(stackUpdateDeterminer).determineHowToUpdateStacks(
            userDefinedFunctionBody,
            mapCopy,
            evalStack,
            programStack
        )
    }
}