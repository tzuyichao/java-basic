package wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.util.Collector;
import wordcount.util.WordCountData;

public class WordCountPojo {

    public static final String PARAM_INPUT = "input";
    public static final String PARAM_OUTPUT = "output";

    public static class Word {
        private String word;
        private int frequency;
        public Word() {}

        public Word(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Word{" +
                    "word='" + word + '\'' +
                    ", frequency=" + frequency +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setGlobalJobParameters(params);

        DataSet<String> text;
        if(params.has(PARAM_INPUT)) {
            text = env.readTextFile(params.get(PARAM_INPUT));
        } else {
            System.out.println("Executing WordCount example with default input data set.");
            System.out.println("Use --input to specify file input.");
            text = WordCountData.getDefaultTextLineDataSet(env);
        }

        DataSet<Word> counts = text.flatMap(new Tokenizer())
                .groupBy("word")
                .reduce(new ReduceFunction<Word>() {
                    @Override
                    public Word reduce(Word val1, Word val2) throws Exception {
                        return new Word(val1.word, val1.frequency + val2.frequency);
                    }
                });

        if(params.has(PARAM_OUTPUT)) {
            counts.writeAsText(params.get(PARAM_OUTPUT), FileSystem.WriteMode.OVERWRITE);
            env.execute("WordCount pojo Example completed");
        } else {
            System.out.println("Printing result to stdout. Use --output to specify output path.");
            counts.print();
        }
    }

    public static final class Tokenizer implements FlatMapFunction<String, Word> {

        @Override
        public void flatMap(String s, Collector<Word> collector) throws Exception {
            String[] tokens = s.toLowerCase().split("\\W+");

            for(String token : tokens) {
                if(token.length() > 0) {
                    collector.collect(new Word(token, 1));
                }
            }
        }
    }
}
