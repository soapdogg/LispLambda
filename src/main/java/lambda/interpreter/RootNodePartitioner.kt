package lambda.interpreter

import lambda.core.constants.FunctionNameConstants

import lambda.core.datamodels.*

class RootNodePartitioner {
    fun partitionRootNodes(
        rootNodes: List<NodeV2>
    ): PartitionedRootNodes {
        val (defun, executables) = rootNodes.partition {
            rootNode -> if (rootNode is ExpressionListNode) {
                val rootNodeAddress = rootNode.children[0]
                if (rootNodeAddress is AtomNode) {
                    return@partition rootNodeAddress.value == FunctionNameConstants.DEFUN
                }
            }
            false
        }

        return PartitionedRootNodes(
            defun.map{it as ExpressionListNode},
            executables
        )
    }
}