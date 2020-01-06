package lib;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;

public final class Tracing {
    private Tracing() {}

    public static JaegerTracer init(String service) {
        Configuration.SamplerConfiguration samplerConfiguration = Configuration.SamplerConfiguration.fromEnv()
                .withType(ConstSampler.TYPE)
                .withParam(1);
        Configuration.ReporterConfiguration reporterConfiguration = Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true);

        Configuration configuration = new Configuration(service)
                .withSampler(samplerConfiguration)
                .withReporter(reporterConfiguration);

        return configuration.getTracer();
    }
}
