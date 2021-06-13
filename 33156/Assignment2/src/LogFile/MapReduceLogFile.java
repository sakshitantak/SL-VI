package LogFile;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.GenericOptionsParser;

public class MapReduceLogFile {
	public static void main(String[] args) throws Exception
	{
		Configuration c = new Configuration();
		String[] files = new GenericOptionsParser(c,args).getRemainingArgs();
		Path input = new Path(files[0]);
		Path output = new Path(files[1]);
		Job j = new Job(c, "MapReduceLogFile");
		j.setJarByClass(MapReduceLogFile.class);
		j.setMapperClass(MapForLogFile.class);
		j.setReducerClass(ReduceForLogFile.class);
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(j, input);
		FileOutputFormat.setOutputPath(j, output);
		System.exit(j.waitForCompletion(true)?0:1);
		
	}
	
	public static class MapForLogFile extends Mapper<LongWritable, Text, Text, IntWritable>{
		public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			String line = value.toString();
			String [] user = line.split(",");
			
			
			
			Text outputKey = new Text(user[0].toUpperCase().trim());
			IntWritable outputValue = new IntWritable(Integer.parseInt(user[1]));
			con.write(outputKey, outputValue);
		}
	}
		
	public static class ReduceForLogFile extends Reducer<Text, IntWritable, Text, IntWritable>{
		int max = 0;
		int min = 0;
		int flag = 0;
		Text maxWord;
		Text minWord;
		
		public void reduce(Text word, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException
		{	
			int sum = 0;
			for(IntWritable value : values)
			{
			  sum += value.get();
			}
			con.write(word, new IntWritable(sum));
			if(sum>max)
				max = sum;
			
			if(flag == 0)
				min = sum;
			else
			{
				if(sum<min)
					min = sum;
			}
			
			flag++;
		}
		
		public void cleanup(Context con) throws IOException, InterruptedException
		{
			con.write(maxWord, null);
		}
	}

}
