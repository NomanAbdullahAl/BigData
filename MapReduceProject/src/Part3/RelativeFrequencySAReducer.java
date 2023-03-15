package Part3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class RelativeFrequencySAReducer extends Reducer<Text, MapWritable, Text, Text> {
    private final Map<String,Integer> _m = new HashMap<>();
    private Map<String,Integer> getMap(){
        _m.clear();
        return _m;
    }
    @Override
    public void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException
    {
        Map<String,Integer> map = this.getMap();
        int count = 0;
        for (MapWritable mapWritable: values) {
            for (Map.Entry<Writable, Writable> entry: mapWritable.entrySet()) {
                String entryKey = entry.getKey().toString();
                int entryValue = ((IntWritable)entry.getValue()).get();
                count+=entryValue;
                if (map.containsKey(entryKey)) {
                    entryValue += map.get(entryKey);
                }
                map.put(entryKey, entryValue);
            }
        }
        context.write(key, new Text(getOutput(map,count)));
    }

    private String getOutput(Map<String,Integer> map, int count){
        StringJoiner result = new StringJoiner(", ", "[", "]");
        map.keySet().forEach(aKey->{
            result.add(String.format("(%s , %f)", aKey, (double) map.get(aKey)/count));
        });
        return result.toString();
    }
}
