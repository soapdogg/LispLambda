package lambda

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.UserDefinedFunction

interface UserDefinedFunctionGenerator {
    fun generateUserDefinedFunction(
        params: ExpressionListNode
    ): Pair<String, UserDefinedFunction>
}