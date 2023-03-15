package Utility;

public enum JobNames {
    WORD_COUNT("WordCount"),
    WORD_COUNT_IMC("WordCountIMC"),
    AVERAGE("Average"),
    AVERAGE_IMC("AverageIMC"),
    RF_PAIR("RFPair"),
    RF_STRIPE("RFStripe");
    private final String jobName;
    private JobNames(String jobName){
        this.jobName = jobName;
    }

    public String getJobName(){
        return jobName;
    }
}
