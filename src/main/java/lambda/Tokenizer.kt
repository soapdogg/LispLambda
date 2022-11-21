package lambda

interface Tokenizer {
    fun tokenize(input: String): List<String>
}