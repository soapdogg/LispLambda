package lambda.evaluator

import lambda.constants.FunctionNameConstants
import lambda.constants.ReservedValuesConstants
import lambda.datamodels.AtomNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.datamodels.ProgramStackItem

class CondChildFunctionEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateCondChildFunction(
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
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