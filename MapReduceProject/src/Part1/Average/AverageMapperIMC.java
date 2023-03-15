package Part1.Average;

import Model.IntPairWritable;
import com.google.common.net.InetAddresses;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AverageMapperIMC extends Mapper<LongWritable, Text, Text, IntPairWritable> {
    private  Map<String, IntPairWritable> map;

    @Override
    protected void setup( Mapper<LongWritable, Text, Text, IntPairWritable>.Context context) {
        map = new HashMap<>();
    }
    @Override
    public void map(LongWritable id, Text record, Context context) throws IOException, InterruptedException
    {
        String[] terms = record.toString().split("\\s+");
        String ip = terms[0];
        String quantity = terms[terms.length - 1];

        if(!InetAddresses.isInetAddress(ip)) return;
        if(!quantity.matches("[0-9]+")) return;

        if (!map.containsKey(ip)) {
            map.put(ip, new IntPairWritable(Integer.valueOf(quantity), 1));
            return;
        }

        IntPairWritable pair = map.get(ip);
        Integer sum = pair.getLeft() + Integer.valueOf(quantity);
        Integer count = pair.getRight() + 1;
        pair.setLeft(sum);
        pair.setRight(count);
        map.put(ip, pair);
    }

    @Override
    protected void cleanup(Mapper<LongWritable, Text, Text, IntPairWritable>.Context context) throws IOException, InterruptedException {
        for(Map.Entry<String, IntPairWritable> entry: map.entrySet()){
            context.write(new Text(entry.getKey()), entry.getValue());
        }
    }
}
