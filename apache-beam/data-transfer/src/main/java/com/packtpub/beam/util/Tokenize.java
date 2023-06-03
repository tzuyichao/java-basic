package com.packtpub.beam.util;

import com.google.common.base.Strings;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Tokenize extends PTransform<PCollection<String>, PCollection<String>> {

    public static Tokenize of() {
        return new Tokenize();
    }

    @Override
    public PCollection<String> expand(PCollection<String> input) {
        PCollection<String> result =
                input.apply(FlatMapElements.into(TypeDescriptors.strings()).via(Tokenize::toWords));
        if (input.hasSchema()) {
            result.setSchema(
                    input.getSchema(),
                    input.getTypeDescriptor(),
                    input.getToRowFunction(),
                    input.getFromRowFunction());
        }
        return result;
    }

    public static List<String> toWords(String input) {
        return Arrays.stream(input.split("\\W+"))
                .filter(((Predicate<String>) Strings::isNullOrEmpty).negate())
                .collect(Collectors.toList());
    }
}