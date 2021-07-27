package lambda.core.datamodels

data class ParserResult(
    val resultingNode: ExpressionListNode,
    val nextIndex: Int
)