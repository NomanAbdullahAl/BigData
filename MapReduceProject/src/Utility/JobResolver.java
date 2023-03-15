package Utility;

import Model.MapReduceJob;
import Part2.RelativeFrequenceyPAJob;
import Part3.RelativeFreqenceySAJob;
import Part1.WordCount.WordCountJob;
import Part1.Average.AverageJob;
import Part1.Average.AverageIMCJob;
import Part1.WordCount.InMapperCombiningWordCountJob;
import java.io.IOException;

public class JobResolver {
    public static MapReduceJob resolve(String jobName) throws IOException {

        if(jobName.equalsIgnoreCase(JobNames.WORD_COUNT.getJobName()))
            return new WordCountJob();
        if(jobName.equalsIgnoreCase( JobNames.WORD_COUNT_IMC.getJobName()))
            return new InMapperCombiningWordCountJob();
        if(jobName.equalsIgnoreCase(JobNames.AVERAGE.getJobName()))
            return new AverageJob();
        if(jobName.equalsIgnoreCase(JobNames.AVERAGE_IMC.getJobName()))
            return new AverageIMCJob();
        if(jobName.equalsIgnoreCase(JobNames.RF_PAIR.getJobName()))
            return new RelativeFrequenceyPAJob();
        if(jobName.equalsIgnoreCase(JobNames.RF_STRIPE.getJobName()))
            return new RelativeFreqenceySAJob();

        throw new IOException(jobName);
    }
}
