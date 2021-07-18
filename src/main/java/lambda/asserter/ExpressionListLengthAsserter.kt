package lambda.asserter

import lambda.constants.FunctionLengthConstants
import lambda.constants.FunctionNameConstants
import lambda.datamodels.AtomNode
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.NodeV2
import lambda.datamodels.UserDefinedFunction
import lambda.exceptions.NotAListException

class ExpressionListLengthAsserter(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val functionLengthMap: Map<String, Int>
) {

    fun assertLengthIsAsExpected(
        nodes: List<NodeV2>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ) {
        nodes.forEach { node ->
            if (node is ExpressionListNode) {
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
                                throw NotAListException("Error! COND parameter: ${condParameter.value} is not a list!${'\n'}")
                            } else {
                                functionLengthAsserter.assertLengthIsAsExpected(
                                    FunctionNameConstants.COND,
                                    FunctionLengthConstants.TWO,
                                    condParameter
                                )
                            }
                        }
                    }
                }
                assertLengthIsAsExpected(
                    node.children,
                    userDefinedFunctions
                )
            }
        }
    }
}