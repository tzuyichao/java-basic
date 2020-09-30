package wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.util.Collector;
import wordcount.util.WordCountData;

public class WordCount {
    public static final String PARAM_INPUT = "input";
    public static final String PARAM_OUTPUT = "output";

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

        DataSet<WordCountPojo.Word> counts = text.flatMap(new WordCountPojo.Tokenizer())
                .groupBy(0)
                .sum(1);

        if(params.has(PARAM_OUTPUT)) {
            counts.writeAsText(params.get(PARAM_OUTPUT), FileSystem.WriteMode.OVERWRITE);
            env.execute("WordCount pojo Example completed");
        } else {
            System.out.println("Printing result to stdout. Use --output to specify output path.");
            counts.print();
        }
    }

    public static final class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] tokens = s.toLowerCase().split("\\W+");

            for(String token : tokens) {
                if(token.length() > 0) {
                    collector.collect(new Tuple2<>(token, 1));
                }
            }
        }
    }
}
