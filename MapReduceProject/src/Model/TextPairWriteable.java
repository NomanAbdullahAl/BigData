package Model;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TextPairWriteable implements WritableComparable<TextPairWriteable> {
    private  Text left;
    private  Text right;
    public TextPairWriteable() {
        left = new Text();
        right = new Text();
    }
    public TextPairWriteable(String l, String r) {
        left = new Text(l);
        right = new Text(r);
    }

    public String getLeft() {
        return left.toString();
    }

    public String getRight() {
        return right.toString();
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

    @Override
    public int compareTo(TextPairWriteable o) {
        final int k = this.left.compareTo(o.left);
        if (k != 0) return k;
        return this.right.compareTo(o.right);
    }

    @Override
    public String toString() {
        return String.format("(%s , %s)", left, right);
    }
}
