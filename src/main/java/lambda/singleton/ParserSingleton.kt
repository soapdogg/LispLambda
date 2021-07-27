package lambda.singleton

import lambda.parser.*
import lambda.parser.internal.ExpressionListNodeParser
import lambda.parser.internal.NodeParser

enum class ParserSingleton {
    INSTANCE;

    private val expressionListNodeParser: ExpressionListNodeParser = ExpressionListNodeParser()
    private val nodeParser: NodeParser = NodeParser(
        expressionListNodeParser
    )
    val parser: Parser = Parser(
        nodeParser
    )

}