package lambda.evaluator;

import lambda.core.datamodels.Stack;

interface StackGenerator {

    <T> Stack<T> generateNewStack(Class<T> clazz);

    static StackGenerator newInstance() {
        return new StackGenerator() {
            @Override
            public <T> Stack<T> generateNewStack(Class<T> clazz) {
                return new Stack<>();
            }
        };
    }
}
