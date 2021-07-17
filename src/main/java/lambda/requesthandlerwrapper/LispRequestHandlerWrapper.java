package lambda.requesthandlerwrapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lambda.datamodels.AtomNode;

public class LispRequestHandlerWrapper implements RequestHandler<String, Void> {

    @Override
    public Void handleRequest(String s, Context context) {
        LambdaLogger logger = context.getLogger();

        logger.log("Body: " + s);

        AtomNode a = new AtomNode(s);

        logger.log("AtomNode.value " + a.getValue());

        return null;
    }
}
