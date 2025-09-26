package json.diff;

import com.deblock.jsondiff.DiffGenerator;
import com.deblock.jsondiff.matcher.*;
import com.deblock.jsondiff.viewer.OnlyErrorDiffViewer;

public class HelloJsonDiff {
    public static void main(String[] args) {
        final var expectedJson = "{\"additionalProperty\":\"a\", \"foo\": \"bar\", \"bar\": \"bar\", \"numberMatch\": 10.0, \"numberUnmatched\": 10.01, \"arrayMatch\": [{\"b\":\"a\"}], \"arrayUnmatched\": [{\"b\":\"a\"}]}";
        final var receivedJson = "{\"foo\": \"foo\", \"bar\": \"bar\", \"numberMatch\": 10, \"numberUnmatched\": 10.02, \"arrayMatch\": [{\"b\":\"a\"}], \"arrayUnmatched\": {\"b\":\"b\"}}";

        final var jsonMatcher = new CompositeJsonMatcher(
                new LenientJsonArrayPartialMatcher(),
                new LenientJsonObjectPartialMatcher(),
                new LenientNumberPrimitivePartialMatcher(new StrictPrimitivePartialMatcher())
        );

        final var jsondiff = DiffGenerator.diff(expectedJson, receivedJson, jsonMatcher);

        final var errorsResult= OnlyErrorDiffViewer.from(jsondiff);

        System.out.println(errorsResult);
        System.out.println(jsondiff.similarityRate());
    }
}
