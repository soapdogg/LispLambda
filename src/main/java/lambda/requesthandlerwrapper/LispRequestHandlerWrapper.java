package lambda.requesthandlerwrapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lambda.Interpreter;
import lambda.ListNotationPrinter;
import lambda.Tokenizer;
import lambda.asserter.AsserterSingleton;
import lambda.evaluator.EvaluatorSingleton;
import lambda.function.FunctionSingleton;
import lambda.interpreter.InterpreterSingleton;
import lambda.parser.ParserSingleton;
import lambda.userdefined.UserDefinedSingleton;

public class LispRequestHandlerWrapper implements RequestHandler<String, Void> {

    private final Interpreter interpreter;

    public LispRequestHandlerWrapper() {
        final var tokenizer = Tokenizer.newInstance();
        final var parser = ParserSingleton.INSTANCE.getParser();
        final var functionLengthAsserter = AsserterSingleton.INSTANCE.getFunctionLengthAsserter();
        final var listNotationPrinter = ListNotationPrinter.newInstance();
        final var functionMap = FunctionSingleton.INSTANCE.getFunctionMap(listNotationPrinter);
        final var userDefinedFunctionGenerator = UserDefinedSingleton.INSTANCE.getUserDefinedFunctionGenerator(
                functionMap,
                functionLengthAsserter
        );

        final var programEvaluator = EvaluatorSingleton.INSTANCE.getProgramEvaluator(
                functionMap
        );

        interpreter = InterpreterSingleton.INSTANCE.getInterpreter(
                tokenizer,
                parser,
                functionLengthAsserter,
                userDefinedFunctionGenerator,
                programEvaluator,
                listNotationPrinter
        );
    }

    @Override
    public Void handleRequest(String input, Context context) {
        LambdaLogger logger = context.getLogger();

        logger.log("input: " + input + "\n");

        String result = interpreter.interpret(input);

        logger.log("result: " + result + "\n");

        return null;
    }
}
