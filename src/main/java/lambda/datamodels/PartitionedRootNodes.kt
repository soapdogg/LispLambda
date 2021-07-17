package lambda.datamodels

data class PartitionedRootNodes(
    val defunNodes: List<ExpressionListNode>,
    val evaluatableNodes: List<NodeV2>
)