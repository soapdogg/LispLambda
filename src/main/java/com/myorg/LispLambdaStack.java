package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.SecretValue;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.pipelines.CdkPipeline;
import software.amazon.awscdk.pipelines.SimpleSynthAction;
import software.amazon.awscdk.pipelines.StandardNpmSynthOptions;
import software.amazon.awscdk.services.codepipeline.Artifact;
import software.amazon.awscdk.services.codepipeline.actions.GitHubSourceAction;
import software.amazon.awscdk.services.codepipeline.actions.GitHubTrigger;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;

import java.util.Collections;

public class LispLambdaStack extends Stack {
    public LispLambdaStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public LispLambdaStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Artifact sourceArtifact = new Artifact();
        final Artifact cloudAssemblyArtifact = new Artifact();

        final CdkPipeline pipeline = CdkPipeline.Builder.create(this, "Pipeline")
            .pipelineName("MyAppPipeline")
            .cloudAssemblyArtifact(cloudAssemblyArtifact)
            .sourceAction(GitHubSourceAction.Builder.create()
                .actionName("GitHub")
                .output(sourceArtifact)
                .oauthToken(SecretValue.secretsManager("LispLambdaStackGithubToken"))
                .trigger(GitHubTrigger.POLL)
                .owner("soapdogg")
                .repo("LispLambda")
                .build())
            .synthAction(SimpleSynthAction.Builder.create()
                .sourceArtifact(sourceArtifact)
                .cloudAssemblyArtifact(cloudAssemblyArtifact)
                .installCommands(Collections.singletonList("npm install -g aws-cdk"))
                .buildCommands(Collections.singletonList("mvn package"))
                .synthCommand("cdk synth")
                .build())
            .build();

        FunctionProps lispLambdaFunctionProps = FunctionProps.builder()
            .functionName("LispInterpreter")
            .code(Code.fromAsset("target/lisp_lambda-0.1.jar"))
            .handler("lambda.requesthandlerwrapper.LispRequestHandlerWrapper")
            .runtime(Runtime.JAVA_11)
            .memorySize(256)
            .timeout(Duration.seconds(300))
            .build();
        new Function(this, "LispInterpreter", lispLambdaFunctionProps);

    }
}
