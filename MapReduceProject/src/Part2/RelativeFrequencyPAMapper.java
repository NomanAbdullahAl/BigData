package Part2;

import Model.TextPairWriteable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RelativeFrequencyPAMapper extends Mapper<LongWritable, Text, TextPairWriteable, IntWritable> {
    private IntWritable one;

    @Override
    protected void setup(Mapper<LongWritable, Text, TextPairWriteable, IntWritable>.Context context) throws IOException, InterruptedException {
        super.setup(context);
        one = new IntWritable(1);
    }

    @Override
    public void map(LongWritable id, Text record, Context context) throws IOException, InterruptedException
    {
        String[] terms = record.toString().split("\\s+");
        for(int i = 0; i < terms.length - 1; i++){
            for(int j = i + 1; j < terms.length; j++) {
                if (terms[i].equals(terms[j])) break;
                context.write(new TextPairWriteable(terms[i], terms[j]), one);
                context.write(new TextPairWriteable(terms[i], "*"), one);
            }
        }
    }
}
