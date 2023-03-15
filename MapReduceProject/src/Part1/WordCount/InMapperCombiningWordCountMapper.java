package Part1.WordCount;

import Model.IntPairWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMapperCombiningWordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private  Map<String, Integer> map;

    @Override
    protected void setup( Mapper<LongWritable, Text, Text, IntWritable>.Context context) {
        map = new HashMap<>();
    }

    @Override
    public void map(LongWritable id, Text record, Mapper.Context context) throws IOException, InterruptedException
    {
        String[] terms = record.toString().split("\\s+");
        for (String term : terms)
        {
            if(term.trim().length() == 0) continue;
            if (map.containsKey(term)) {
                map.put(term, map.get(term) + 1);
            } else {
                map.put(term, 1);
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            context.write(new Text(entry.getKey()), new IntWritable(entry.getValue()));
        }
    }
}
