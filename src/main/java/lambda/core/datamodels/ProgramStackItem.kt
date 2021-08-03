package lambda.core.datamodels

data class ProgramStackItem(
    val functionExpressionNode: ExpressionListNode,
    val currentParameterIndex: Int,
    val variableMap: Map<String, Node>,
    val functionName: String
)