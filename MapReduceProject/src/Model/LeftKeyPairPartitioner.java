package Model;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class LeftKeyPairPartitioner extends Partitioner<TextPairWriteable, IntWritable> {
    @Override
    public int getPartition(TextPairWriteable key, IntWritable value, int numberOfReducer) {
        if(numberOfReducer <= 0) return 0;
        return Math.abs(key.getLeft().hashCode()) % numberOfReducer;
    }
}
