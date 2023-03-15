@ECHO OFF
hadoop fs -mkdir /input
ECHO "/input directory created"

hadoop fs -mkdir /output
ECHO "/output directory created"
hadoop fs -copyFromLocal ./word_count.txt /input/word_count.txt
ECHO "copied word_count input file from local to hdfs"


ECHO "executing WordCount Job"
hadoop jar ../../Jar/MapReduceProject.jar WordCount /input/word_count.txt /output/word_count.txt

ECHO "Output of WordCount Job"
hadoop fs -cat /output/word_count.txt/part*

ECHO "executing WordCountIMC Job"
hadoop jar ../../Jar/MapReduceProject.jar WordCount /input/word_count.txt /output/word_count_imc.txt

ECHO "Output of WordCountIMC Job"
hadoop fs -cat /output/word_count_imc.txt/part*


hadoop fs -copyFromLocal ./access_log /input/access_log.txt
ECHO "Copied access_log input file from local to hdfs"

ECHO "executing Average Job"
hadoop jar ../../Jar/MapReduceProject.jar Average /input/access_log.txt /output/access_log.txt

ECHO "Output of Average Job"
hadoop fs -cat /output/access_log.txt/part*

ECHO "Executing AverageIMC Job"
hadoop jar ../../Jar/MapReduceProject.jar AverageIMC /input/access_log.txt /output/access_log_imc.txt

ECHO "Output of AverageIMC Job"
hadoop fs -cat /output/access_log_imc.txt/part*

PAUSE


