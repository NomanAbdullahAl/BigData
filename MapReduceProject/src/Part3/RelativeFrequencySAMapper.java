package Part3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RelativeFrequencySAMapper extends Mapper<LongWritable, Text, Text, MapWritable> {
    final private IntWritable one = new IntWritable(1);
    @Override
    public void map(LongWritable id, Text record, Context context) throws IOException, InterruptedException
    {
        String[] terms = record.toString().split("\\s+");
        for(int i = 0; i < terms.length - 1; i++){
            for(int j = i + 1; j < terms.length; j++) {
                if (terms[i].equals(terms[j])) break;
                //As there identical value on the same window
                MapWritable map = new MapWritable();
                map.put(new Text(terms[j]), one);
                context.write(new Text(terms[i]), map);
            }
        }
    }
}
