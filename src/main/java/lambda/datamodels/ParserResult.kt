package lambda.datamodels

data class ParserResult(
    val resultingNode: ExpressionListNode,
    val nextIndex: Int
)