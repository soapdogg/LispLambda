package lambda.evaluator

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.*
import lambda.core.datamodels.Stack
import lambda.Function
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.lang.Exception
import kotlin.collections.HashMap

class FinishedProgramStackItemEvaluatorTest {

    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)
    private val functionMap = HashMap<String, Function>()
    private val builtInFunctionEvaluator = Mockito.mock(BuiltInFunctionEvaluator::class.java)
    private val userDefinedFunctionEvaluator = Mockito.mock(UserDefinedFunctionEvaluator::class.java)

    private val finishedProgramStackItemEvaluator = FinishedProgramStackItemEvaluatorImpl(
        postEvaluationStackUpdater,
        functionMap,
        builtInFunctionEvaluator,
        userDefinedFunctionEvaluator
    )

    @Test
    fun functionNameIsNilTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(Node::class.java)
        val child1 = Mockito.mock(Node::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = ReservedValuesConstants.NIL
        Mockito.`when`(top.functionName).thenReturn(functionName)

        Assertions.assertDoesNotThrow {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            child1,
            variableMap,
            evalStack,
            programStack
        )


        Mockito.verifyNoInteractions(builtInFunctionEvaluator)
        Mockito.verifyNoInteractions(userDefinedFunctionEvaluator)
    }

    @Test
    fun functionNameIsBuiltInFunctionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(Node::class.java)
        val child1 = Mockito.mock(Node::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = FunctionNameConstants.LESS
        Mockito.`when`(top.functionName).thenReturn(functionName)

        val function = Mockito.mock(Function::class.java)
        functionMap[functionName] = function

        val functionStack = Stack<Node>()

        Assertions.assertDoesNotThrow {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        /*Mockito.verify(builtInFunctionEvaluator).evaluateBuiltInFunction(
            functionName,
            functionStack,
            top,
            evalStack,
            programStack
        )*/

        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
        Mockito.verifyNoInteractions(userDefinedFunctionEvaluator)
    }

    @Test
    fun functionNameIsUserDefinedFunctionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(Node::class.java)
        val child1 = Mockito.mock(Node::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = "userDefinedFunction"
        Mockito.`when`(top.functionName).thenReturn(functionName)

        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)

        val userDefinedFunctions = mapOf(
            Pair(functionName, userDefinedFunction)
        )

        val functionStack = Stack<Node>()

        Assertions.assertDoesNotThrow {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        /*Mockito.verify(userDefinedFunctionEvaluator).evaluateUserDefinedFunction(
            userDefinedFunction,
            variableMap,
            functionStack,
            evalStack,
            programStack
        )*/


        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
        Mockito.verifyNoInteractions(builtInFunctionEvaluator)
    }

    @Test
    fun functionNameIsInvalidCarValueTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val evalStack = Stack<Node>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(Node::class.java)
        val child1 = Mockito.mock(Node::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = "invalid"
        Mockito.`when`(top.functionName).thenReturn(functionName)

        Assertions.assertThrows(
            Exception::class.java
        ) {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
        Mockito.verifyNoInteractions(builtInFunctionEvaluator)
        Mockito.verifyNoInteractions(userDefinedFunctionEvaluator)
    }
}