package lambda.requesthandlerwrapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.Logger;

public class LispRequestHandlerWrapper implements RequestHandler<String, Void> {

    @Override
    public Void handleRequest(String s, Context context) {
        LambdaLogger logger = context.getLogger();

        logger.log("Body: " + s);

        return null;
    }
}
