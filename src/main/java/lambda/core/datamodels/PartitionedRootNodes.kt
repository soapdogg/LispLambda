package lambda.core.datamodels

data class PartitionedRootNodes(
    val defunNodes: List<ExpressionListNode>,
    val evaluatableNodes: List<NodeV2>
)