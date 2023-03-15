@ECHO OFF
hadoop fs -rm -r /input
hadoop fs -mkdir /input
ECHO "/input directory created"

hadoop fs -rm -r /output
hadoop fs -mkdir /output
ECHO "/output directory created"

hadoop fs -copyFromLocal ./TestData.txt /input/TestData.txt
ECHO "copied TestData input file from local to hdfs"

ECHO "executing Output of Relative Frequencey Stripe Approach Job"
hadoop jar ../../Jar/MapReduceProject.jar RFStripe /input/TestData.txt /output/TestData.txt

ECHO "Output of Relative Frequencey Stripe Approach Job"
hadoop fs -cat /output/TestData.txt/part*