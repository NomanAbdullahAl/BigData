package Part2;

import Model.TextPairWriteable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class RelativeFrequencyPAReducer extends Reducer<TextPairWriteable, IntWritable, TextPairWriteable, DoubleWritable> {
    private int totalPairs;

    @Override
    protected void setup(Reducer<TextPairWriteable, IntWritable, TextPairWriteable, DoubleWritable>.Context context) throws IOException, InterruptedException {
        super.setup(context);
        totalPairs = 0;
    }

    @Override
    public void reduce(TextPairWriteable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
    {
        int count = 0;
        for(IntWritable v: values) count += v.get();
        if(key.getRight().equals("*")) {
            totalPairs = count;
            return;
        }
        context.write(key, new DoubleWritable((double) count/totalPairs));
    }
}
