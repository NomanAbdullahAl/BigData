package Part1.Average;

import Model.MapReduceJob;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AverageJob extends MapReduceJob<AverageMapper, AverageReducer, Text, IntWritable> {
    public AverageJob() throws IOException {
        super("Average", AverageMapper.class, AverageReducer.class, Text.class, IntWritable.class);
    }
}
