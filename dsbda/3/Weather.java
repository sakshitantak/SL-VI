package weather;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;

import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.GenericOptionsParser;

public class Weather {

public static void main(String [] args) throws Exception

{

Configuration c=new Configuration();

String[] files=new GenericOptionsParser(c,args).getRemainingArgs();

Path input=new Path(files[0]);

Path output=new Path(files[1]);

Job j=new Job(c,"weather");

j.setJarByClass(Weather.class);

j.setMapperClass(MapForWeather.class);

j.setReducerClass(ReduceForWeather.class);

j.setOutputKeyClass(Text.class);

j.setOutputValueClass(DoubleWritable.class);

FileInputFormat.addInputPath(j, input);

FileOutputFormat.setOutputPath(j, output);

System.exit(j.waitForCompletion(true)?0:1);

}

public static class MapForWeather extends Mapper<LongWritable, Text, Text, DoubleWritable>{

	public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
	
		String line = value.toString();
		String[] words=line.split(",");
		Text outputKey = null;
		
		for(int i=0;i<words.length;i=i+9)
		{
		outputKey = new Text(words[i].trim());
		double min = Double.parseDouble(words[i+1]) + Double.parseDouble(words[i+3]) + Double.parseDouble(words[i+5]) + Double.parseDouble(words[i+7]);
		double max = Double.parseDouble(words[i+2]) + Double.parseDouble(words[i+4]) + Double.parseDouble(words[i+6]) + Double.parseDouble(words[i+8]);
		double mean = (min+max)/8;
		con.write(outputKey, new DoubleWritable(mean));
		}	
	}
}


public static class ReduceForWeather extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	double max_temp=0.0;
	double min_temp=999.0;
	Text hottest=new Text();
	Text coolest=new Text();
	public void reduce(Text word, Iterable<DoubleWritable> values, Context con) throws IOException, InterruptedException{
	
		double sum = 0;
	
	   for(DoubleWritable value : values)
	   {
		   sum += value.get();
	   }
	   if(sum > max_temp) {
			max_temp = sum;
			hottest.set(word);
		}
		if(sum < min_temp) {
			min_temp = sum;
			coolest.set(word);
		}  
	}
	protected void cleanup(Context con) throws IOException, InterruptedException {
		con.write(hottest, new DoubleWritable(max_temp));
		con.write(coolest, new DoubleWritable(min_temp));
	}
}

}
