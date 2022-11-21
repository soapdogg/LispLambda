package lambda

import lambda.core.datamodels.Node

interface Parser {
    fun parse(tokens: List<String>): List<Node>
}