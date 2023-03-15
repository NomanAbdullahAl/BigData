package Part3;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RelativeFrequencySAMapper extends Mapper<LongWritable, Text, Text, MapWritable> {
   private final Map<String,Integer> _m = new HashMap<>();
   private Map<String,Integer>getMap(){
       _m.clear();
       return _m;
   }
    @Override
    public void map(LongWritable id, Text record, Context context) throws IOException, InterruptedException
    {
        String[] terms = record.toString().split("\\s+");
        for(int i = 0; i < terms.length - 1; i++){
            Map<String,Integer> map = getMap();
            for(int j = i + 1; j < terms.length; j++) {
                if (terms[i].equals(terms[j])) break;
                if(map.containsKey(terms[j])){
                    map.put(terms[j],map.get(terms[j])+1);
                }else
                {
                    map.put(terms[j],1);
                }
            }
            context.write(new Text(terms[i]), getMapWritable(map));
        }
    }
    private MapWritable getMapWritable(Map<String,Integer> map){
        MapWritable mapWritable = new MapWritable();
        map.forEach((key,val)->{
            mapWritable.put(new Text(key),new IntWritable(val));
        });
        return mapWritable;
    }
}
