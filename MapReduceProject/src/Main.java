
import Utility.JobResolver;
import Model.MapReduceJob;
import org.apache.hadoop.util.ToolRunner;

public class Main {
    public static void main(String[] args) throws Exception {
        MapReduceJob job = JobResolver.resolve(args[0]);
        final int statusCode = ToolRunner.run(job, args);
        System.exit(statusCode);
    }
}
