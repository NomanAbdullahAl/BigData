package Part1.Average;

import com.google.common.net.InetAddresses;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable id, Text record, Context context) throws IOException, InterruptedException
    {
        String[] terms = record.toString().split("\\s+");
        String ip = terms[0];
        String quantity = terms[terms.length - 1];
        if(!InetAddresses.isInetAddress(ip)) return;
        if(!quantity.matches("[0-9]+")) return;
        context.write(new Text(ip), new IntWritable(Integer.valueOf(quantity)));
    }
}
