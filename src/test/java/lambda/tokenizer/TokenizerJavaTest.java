package lambda.tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class TokenizerJavaTest {

    @Test
    void tokenizeLineTest() {
        final var word1 = "word1";
        final var word2 = "word2";
        final var line =  "\t" + word1 + "    " + word2 + "\n";

        final var wordTokenizer = Mockito.mock(WordTokenizer.class);

        final var tokenizer = TokenizerJava.newInstance(wordTokenizer);

        Mockito.when(wordTokenizer.tokenizeWord(word1)).thenReturn(List.of(word1));
        Mockito.when(wordTokenizer.tokenizeWord(word2)).thenReturn(List.of(word2));

        final var actual = tokenizer.tokenize(line);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(word1, actual.get(0));
        Assertions.assertEquals(word2, actual.get(1));
    }

}
