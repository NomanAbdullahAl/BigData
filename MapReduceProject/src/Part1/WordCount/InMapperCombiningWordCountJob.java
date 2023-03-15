package Part1.WordCount;

import Model.MapReduceJob;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class InMapperCombiningWordCountJob extends MapReduceJob<InMapperCombiningWordCountMapper, WordCountReducer, Text, IntWritable> {

    public InMapperCombiningWordCountJob() throws IOException {
        super("In Mapper Combining Part1.WordCount", InMapperCombiningWordCountMapper.class, WordCountReducer.class, Text.class, IntWritable.class);
    }
}
