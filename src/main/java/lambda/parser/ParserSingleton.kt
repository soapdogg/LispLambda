package lambda.parser

import lambda.Parser

enum class ParserSingleton {
    INSTANCE;

    fun getParser(): Parser {
        val nodeParser = NodeParserImpl()
        return ParserImpl(
            nodeParser
        )
    }
}