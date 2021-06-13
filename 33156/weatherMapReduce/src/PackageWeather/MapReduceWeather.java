package PackageWeather;

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


public class MapReduceWeather {
	public static void main(String[] args) throws Exception
	{
		Configuration c = new Configuration();
		String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
		Path input = new Path(files[0]);
		Path output = new Path(files[1]);
		Job j = new Job(c, "MapReduceWeather");
		j.setJarByClass(MapReduceWeather.class);
		j.setMapperClass(MapForWeather.class);
		j.setReducerClass(ReduceForWeather.class);
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(j, input);
		FileOutputFormat.setOutputPath(j, output);
		System.exit(j.waitForCompletion(true)?0:1);
	}
	
	public static class MapForWeather extends Mapper<LongWritable, Text, Text, IntWritable>{
		public void map1901(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			String line = value.toString();
			
		}
		
		public void map1902(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1903(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1904(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1905(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1906(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1907(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1908(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1909(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1910(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1911(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1912(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1913(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1914(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1915(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1916(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1917(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1918(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1919(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		public void map1920(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
			
		}
		
		
	}
	
	public static class ReduceForWeather extends Reducer<Text, IntWritable, Text, IntWritable>{
		
	}

}
