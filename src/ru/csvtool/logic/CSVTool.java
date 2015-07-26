package ru.csvtool.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InvalidPropertiesFormatException;

import ru.csvtool.holder.PropertiesHolder;

public class CSVTool {
	public static final String DEFAULT_CONFIG_FILE = "conf/config.xml";
	
	
	public static void main(String args[])
	{
		/* initialize properties */
		PropertiesHolder properties = null;
		try
		{
			String configFileName = (args.length > 0 ? args[0] : DEFAULT_CONFIG_FILE);
			System.out.println("Initializing properties: " + configFileName);
			properties = new PropertiesHolder(configFileName);
		}
		catch(InvalidPropertiesFormatException e)
		{
			System.err.println("ERROR: xml bad format! " + e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println("ERROR: IO Exception! " + e.getMessage());
		}

		/* initialize output file */
		PrintWriter output = null;
		try
		{
			System.out.println("Initializing output file: " + properties.getOutputFile());
			output = new PrintWriter(properties.getOutputFile());

			for(int i = 1; i <= properties.getAmount(); i++)
			{
				output.println(properties.getTemplate().replace(properties.getMask(), String.valueOf(i)));
			}
			System.out.println("completed successfully.");
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: output file not found! " + e.getMessage());
		}
		finally
		{
			if(output != null)
				try{output.close();} catch(Exception ignored){/*ignored*/}
		}
	}
}
