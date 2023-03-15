package Part1.Average;

import Model.IntPairWritable;
import Model.MapReduceJob;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AverageIMCJob extends MapReduceJob<AverageMapperIMC, AverageReducerIMC, Text, IntPairWritable> {
    public AverageIMCJob() throws IOException {
        super("In-mapper combining Part1.Average", AverageMapperIMC.class, AverageReducerIMC.class, Text.class, IntPairWritable.class);
    }
}