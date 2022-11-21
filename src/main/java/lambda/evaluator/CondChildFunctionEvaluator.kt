package lambda.evaluator

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

internal interface CondChildFunctionEvaluator {
    fun evaluateCondChildFunction(
        top: ProgramStackItem,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    )
}

internal class CondChildFunctionEvaluatorImpl(
    private val stackUpdateDeterminer: StackUpdateDeterminer
): CondChildFunctionEvaluator {

    override fun evaluateCondChildFunction(
        top: ProgramStackItem,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    ) {
        programStack.push(top)
        val condChildCurrentParam = top.functionExpressionNode.children[top.currentParameterIndex +1]
        if (top.currentParameterIndex == 0) {
            stackUpdateDeterminer.determineHowToUpdateStacks(
                condChildCurrentParam,
                top.variableMap,
                evalStack,
                programStack
            )
        }
        else {
            programStack.pop()
            val evaluatedCondChild = evalStack.pop() as AtomNode
            if (evaluatedCondChild.value != ReservedValuesConstants.NIL) {
                while (
                    programStack.peek().functionName != FunctionNameConstants.COND
                ) {
                    programStack.pop()
                }
                programStack.pop() //CondProgramStackItem
                stackUpdateDeterminer.determineHowToUpdateStacks(
                    condChildCurrentParam,
                    top.variableMap,
                    evalStack,
                    programStack
                )
            }
        }
    }
}