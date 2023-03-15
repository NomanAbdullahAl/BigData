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

    private int count;
    private Map<String,Integer> map;

    @Override
    protected void setup(Reducer<Text, MapWritable, Text, Text>.Context context) throws IOException, InterruptedException {
        super.setup(context);
        count = 0;
        map = new HashMap<>();
    }
    private void cleanUp(){
        count = 0;
        map.clear();
    }
    @Override
    public void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException
    {
        this.cleanUp();
        for (MapWritable mapWritable: values) {
            for (Map.Entry<Writable, Writable> entry: mapWritable.entrySet()) {
                String entryKey = entry.getKey().toString();
                int entryValue = ((IntWritable)entry.getValue()).get();
                if (map.containsKey(entryKey)) {
                    entryValue += map.get(entryKey);
                }
                map.put(entryKey, entryValue);
            }
            count += mapWritable.size();
        }
        context.write(key, new Text(getOutput(map)));
    }

    private String getOutput(Map<String,Integer> map){
        StringJoiner result = new StringJoiner(", ", "[", "]");
        map.keySet().forEach(aKey->{
            result.add(String.format("(%s , %f)", aKey, (double) map.get(aKey)/count));
        });
        return result.toString();
    }
}
