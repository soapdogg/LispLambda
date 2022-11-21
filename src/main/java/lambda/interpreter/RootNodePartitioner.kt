package lambda.interpreter

import lambda.core.constants.FunctionNameConstants

import lambda.core.datamodels.*

internal interface RootNodePartitioner {
    fun partitionRootNodes(
        rootNodes: List<Node>
    ): PartitionedRootNodes
}

internal class RootNodePartitionerImpl: RootNodePartitioner {
    override fun partitionRootNodes(
        rootNodes: List<Node>
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