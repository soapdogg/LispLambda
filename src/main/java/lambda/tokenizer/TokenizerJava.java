package lambda.tokenizer;

import lambda.Tokenizer;
import lambda.core.constants.TokenValueConstants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface TokenizerJava extends Tokenizer {

    static TokenizerJava newInstance(WordTokenizer wordTokenizer) {

        return input -> {
            final var trimmedLine = input.trim();
            final var words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN);
            return Arrays.stream(words).map(
                    wordTokenizer::tokenizeWord
            ).flatMap(List::stream).collect(Collectors.toList());
        };
    }
}


