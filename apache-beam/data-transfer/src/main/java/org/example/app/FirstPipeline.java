package org.example.app;

import com.packtpub.beam.util.PrintElements;
import com.packtpub.beam.util.Tokenize;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Example from Building-Big-Data-Pipelines-with-Apache-Beam
 */
public class FirstPipeline {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ClassLoader loader = FirstPipeline.class.getClassLoader();
        URI file = loader.getResource("lorem.txt").toURI();
        List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);

        Pipeline pipeline = Pipeline.create();
        PCollection<String> input = pipeline.apply(Create.of(lines));
        PCollection<String> words = input.apply(Tokenize.of());
        PCollection<KV<String, Long>> result = words.apply(Count.perElement());
        result.apply(PrintElements.of());
        pipeline.run().waitUntilFinish();
    }
}
