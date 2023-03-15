package Part2;

import Model.MapReduceJob;
import Model.LeftKeyPairPartitioner;
import Model.TextPairWriteable;
import org.apache.hadoop.io.IntWritable;

import java.io.IOException;

public class RelativeFrequenceyPAJob extends MapReduceJob<RelativeFrequencyPAMapper, RelativeFrequencyPAReducer, TextPairWriteable, IntWritable> {

    public RelativeFrequenceyPAJob() throws IOException {
        super("Relative Frequencies Job using Pair Approach",
                RelativeFrequencyPAMapper.class, RelativeFrequencyPAReducer.class, TextPairWriteable.class, IntWritable.class);
        setPartitioner(LeftKeyPairPartitioner.class);
    }
}
