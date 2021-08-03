package lambda.singleton

import lambda.parser.*
import lambda.parser.internal.NodeParser

enum class ParserSingleton {
    INSTANCE;

    private val nodeParser: NodeParser = NodeParser()
    val parser: Parser = Parser(
        nodeParser
    )

}