package Part1.Average;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
    private DoubleWritable result = new DoubleWritable();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
    {
        int sum = 0, count = 0;
        for (IntWritable val : values)
        {
            sum += val.get();
            count += 1;
        }
        result.set((double) sum/count);
        context.write(key, result);
    }
}
