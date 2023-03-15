package Part1.WordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Arrays;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    public void map(LongWritable id, Text record, Context context) {
        String[] terms = record.toString().split("\\s+");
        Arrays.stream(terms).forEach(t->{
            if(t.trim().length()>0){
                word.set(t);
                try {
                    context.write(word,one);
                } catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
    }
}
