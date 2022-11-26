package lambda;

import lambda.tokenizer.TokenizerSingleton;

import java.util.List;

public interface Tokenizer {

    List<String> tokenize(String line);

    static Tokenizer newInstance() {
        return TokenizerSingleton.INSTANCE.getTokenizer();
    }
}
