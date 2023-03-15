package Part3;

import Model.MapReduceJob;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class RelativeFreqenceySAJob extends MapReduceJob<RelativeFrequencySAMapper, RelativeFrequencySAReducer, Text, MapWritable> {
    public RelativeFreqenceySAJob() throws IOException {
        super("Relative Frequencies Job using Stripe Approach",
                RelativeFrequencySAMapper.class,
                RelativeFrequencySAReducer.class,
                Text.class,
                MapWritable.class);
    }
}
