import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.sink.SocketClientSink;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class WindowWordCount {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.socketTextStream("localhost", 9999).process(new Splitter()).writeToSocket("localhost", 9998, new SimpleStringSchema());
        env.execute("Window WordCount");
    }

    public static class Splitter extends ProcessFunction<String, String> {

        @Override
        public void processElement(String sentence, Context context, Collector<String> out) throws Exception {
            System.out.println("Received sentence: " + sentence);
            for (String word: sentence.split(" ")) {
                out.collect(word);
            }
        }
    }

}