package lambda.core.datamodels

data class UserDefinedFunction(
    val formalParameters: List<String>,
    val body: NodeV2
)