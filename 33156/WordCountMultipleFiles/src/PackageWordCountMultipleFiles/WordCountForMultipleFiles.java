package PackageWordCountMultipleFiles;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.Mapper.Context;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.GenericOptionsParser;

//import mutipleInput.Join;

import org.apache.hadoop.conf.Configured;

import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import org.apache.hadoop.util.Tool;

import org.apache.hadoop.util.ToolRunner;


public class WordCountForMultipleFiles extends Configured implements Tool 
{

 public static class CounterMapper extends Mapper <LongWritable, Text, Text, IntWritable>
 {
  public void map(LongWritable key, Text value, Context context)
  throws IOException, InterruptedException
  {
   String[] line=value.toString().split("\t"); 
   
   Text outputKey = new Text(line[0].toUpperCase().trim());
   IntWritable outputValue = new IntWritable(Integer.parseInt(line[1]));

   context.write(outputKey, outputValue);
  }
 }


 public static class CountertwoMapper extends Mapper <LongWritable, Text, Text, IntWritable>
 {
  public void map(LongWritable key, Text value, Context context)
  throws IOException, InterruptedException
  {
   String[] line=value.toString().split("\t");
   
   Text outputKey = new Text(line[0].toUpperCase().trim());
   IntWritable outputValue = new IntWritable(Integer.parseInt(line[1]));
   
   context.write(outputKey, outputValue);
  }
 }

 public static class CounterReducer extends Reducer <Text, IntWritable, Text, IntWritable>
 {
  String line=null;

  public void reduce(Text key, Iterable values, Context context ) 
  throws IOException, InterruptedException
  {
	  
	  int sum = 0;
	  
	  for( value : values)
	  {
		  line = value.toString();
	  }

	  context.write(key, new Text(line));
 }
}


 public int run(String[] args) throws Exception {
 Configuration conf = new Configuration();
 Job job = new Job(conf, "WordCountMultipleFiles");
 job.setJarByClass(WordCountForMultipleFiles.class);
 MultipleInputs.addInputPath(job,new Path(args[0]),TextInputFormat.class,CounterMapper.class);
 MultipleInputs.addInputPath(job,new Path(args[1]),TextInputFormat.class,CountertwoMapper.class);
  
 FileOutputFormat.setOutputPath(job, new Path(args[2]));
 job.setReducerClass(CounterReducer.class);
 job.setNumReduceTasks(1);
 job.setOutputKeyClass(Text.class);
 job.setOutputValueClass(Text.class);
 
 return (job.waitForCompletion(true) ? 0 : 1);

 }

 public static void main(String[] args) throws Exception {

  
  int ecode = ToolRunner.run(new WordCountForMultipleFiles(), args);
  System.exit(ecode);

  
 }

}