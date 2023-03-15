package Part1.WordCount;

import Model.MapReduceJob;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class WordCountJob
        extends MapReduceJob<WordCountMapper, WordCountReducer, Text, IntWritable> {

    public WordCountJob() throws IOException {
        super("WordCount",
                WordCountMapper.class,
                WordCountReducer.class,
                Text.class,
                IntWritable.class);
    }
}
