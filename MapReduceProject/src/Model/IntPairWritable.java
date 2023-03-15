package Model;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntPairWritable implements Writable {
    private IntWritable left;
    private IntWritable right;

    public IntPairWritable() {
        this.left = new IntWritable(0);
        this.right = new IntWritable(0);
    }
    public IntPairWritable(int left, int right) {
        this.left = new IntWritable(left);
        this.right = new IntWritable(right);
    }
    public int getLeft() {
        return left.get();
    }

    public int getRight() {
        return right.get();
    }

    public void setRight(int right) {
        this.right.set(right);
    }

    public void setLeft(int left) {
        this.left.set(left);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        left.write(dataOutput);
        right.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        left.readFields(dataInput);
        right.readFields(dataInput);
    }
}
