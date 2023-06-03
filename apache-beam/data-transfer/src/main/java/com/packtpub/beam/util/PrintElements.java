package com.packtpub.beam.util;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

/** Print contents of {@link PCollection} to {@link System.err} */
public class PrintElements<T> extends PTransform<PCollection<T>, PDone> {

    public static <T> PrintElements<T> of() {
        return new PrintElements<>();
    }

    @Override
    public PDone expand(PCollection<T> input) {
        input.apply(ParDo.of(new LogResultsFn<>()));
        return PDone.in(input.getPipeline());
    }

    private static class LogResultsFn<T> extends DoFn<T, Void> {
        @ProcessElement
        public void process(@Element T elem) {
            System.err.println(elem);
        }
    }
}