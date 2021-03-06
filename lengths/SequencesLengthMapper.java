package lengths;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

/**
 * 
 * @author Gianluca Roscigno - email: giroscigno@unisa.it - http://www.di.unisa.it/~roscigno/
 * 
 * @version 1.0
 * 
 * Date: January, 30 2015
 */

public  class SequencesLengthMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final Text word = new Text();
	private final IntWritable subsize = new IntWritable();
	private Counter countErrors;
	private Counter countFunctions;

	@Override
	protected void setup(
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
					throws IOException, InterruptedException {

		super.setup(context);

		countErrors = context.getCounter("SequencesLength Mapper","errors");
		countErrors.setValue(0);

		countFunctions = context.getCounter("SequencesLength Mapper","number of functions");
		countFunctions.setValue(0);
	}

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {


		countFunctions.increment(1);

		try{

			String[] lines = value.toString().split("[\t]");

			if(lines.length>=2){
				word.set(lines[0]);
				subsize.set(Integer.parseInt(lines[1]));
				context.write(word, subsize);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			countErrors.increment(1);
		}

	}

}