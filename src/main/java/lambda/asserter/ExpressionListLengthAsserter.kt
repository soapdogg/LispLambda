package lambda.asserter

import lambda.core.constants.FunctionLengthConstants
import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.*
import lambda.core.exceptions.NotAListException

class ExpressionListLengthAsserter(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val functionLengthMap: Map<String, Int>,
    private val minimumFunctionLengthMap: Map<String, Int>
) {

    fun assertLengthIsAsExpected(
        nodes: List<Node>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ) {
        val stack = Stack<ExpressionListNode>()
        nodes.filterIsInstance<ExpressionListNode>().forEach{
            stack.push(it)
        }
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            val address = node.children[0]
            if(address is AtomNode) {
                val addressValue = address.value
                functionLengthMap[addressValue]?.let {
                    functionLengthAsserter.assertLengthIsAsExpected(
                        addressValue,
                        it,
                        node
                    )
                }
                minimumFunctionLengthMap[addressValue]?.let{
                    functionLengthAsserter.assertLengthIsAtLeastMinimum(
                        addressValue,
                        it,
                        node
                    )
                }
                userDefinedFunctions[addressValue]?.let {
                    functionLengthAsserter.assertLengthIsAsExpected(
                        addressValue,
                        it.formalParameters.size + 1,
                        node
                    )
                }
                if (addressValue == FunctionNameConstants.COND) {
                    for (i in 1 until node.children.size - 1) {
                        val condParameter = node.children[i]
                        if (condParameter is AtomNode) {
                            throw NotAListException("Error! cond parameter: ${condParameter.value} is not a list!${'\n'}")
                        } else {
                            functionLengthAsserter.assertLengthIsAsExpected(
                                FunctionNameConstants.COND,
                                FunctionLengthConstants.TWO,
                                condParameter
                            )
                        }
                    }
                }
                node.children.filterIsInstance<ExpressionListNode>().forEach{
                    stack.push(it)
                }
            }
        }
    }
}