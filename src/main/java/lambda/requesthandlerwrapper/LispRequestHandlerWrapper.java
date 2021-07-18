package lambda.requesthandlerwrapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lambda.interpreter.Interpreter;
import lambda.singleton.InterpreterSingleton;

public class LispRequestHandlerWrapper implements RequestHandler<String, Void> {

    private final Interpreter interpreter;

    public LispRequestHandlerWrapper() {
        interpreter = InterpreterSingleton.INSTANCE.getInterpreter();
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
