package Part1.Average;

import Model.IntPairWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducerIMC extends Reducer<Text, IntPairWritable, Text, DoubleWritable> {

    @Override
    public void reduce(Text key, Iterable<IntPairWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0, count = 0;
        for(IntPairWritable val: values) {
            sum += val.getLeft();
            count += val.getRight();
        }
        context.write(key, new DoubleWritable((double) sum/count));
    }
}
