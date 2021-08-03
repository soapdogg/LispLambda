package lambda.evaluator.rootnode.internal

import lambda.core.constants.FunctionNameConstants
import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.evaluator.rootnode.internal.StackUpdateDeterminer

class CondChildFunctionEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateCondChildFunction(
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