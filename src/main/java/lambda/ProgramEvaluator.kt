package lambda

import lambda.core.datamodels.Node
import lambda.core.datamodels.UserDefinedFunction

interface ProgramEvaluator {
    fun evaluate(
        rootNodes: List<Node>,
        userDefinedFunctions: Map<String, UserDefinedFunction>
    ): List<Node>
}