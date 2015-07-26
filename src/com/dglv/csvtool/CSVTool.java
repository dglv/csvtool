package com.dglv.csvtool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InvalidPropertiesFormatException;

import com.dglv.csvtool.holder.PropertiesHolder;

public class CSVTool {
	private static final String DEFAULT_CONFIG_FILE_NAME = "config.xml";

	public static void main(String args[])
	{
		final String configFileName= args.length > 0 ? args[0] : DEFAULT_CONFIG_FILE_NAME;

		if(!new File(configFileName).isFile())
		{
			System.out.println("Configuration file not found");
			System.out.println("usage: csvtool.jar " + DEFAULT_CONFIG_FILE_NAME);
			System.exit(0);
		}

		/* initialize properties */
		PropertiesHolder properties = null;
		try
		{
			System.out.println("Initializing properties: " + configFileName);
			properties = new PropertiesHolder(configFileName);
		}
		catch(InvalidPropertiesFormatException e)
		{
			System.err.println("ERROR: xml bad format " + e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println("ERROR: IO Exception" + e.getMessage());
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
