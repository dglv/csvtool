package com.dglv.csvtool.holder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesHolder {
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_MASK = "mask";
	public static final String KEY_TEMPLATE = "template";
	public static final String KEY_OUTPUT_FILE = "output.file";
	Properties properties;
	
	public PropertiesHolder(String pathname) throws InvalidPropertiesFormatException, IOException
	{
		FileInputStream fs = null;
		try
		{
			properties = new Properties();
			fs = new FileInputStream(pathname);
			properties.loadFromXML(fs);
		}
		finally
		{
			if(fs != null)
				try{fs.close();} catch(Exception ignored){/*ignored*/}
		}
	}
	
	public Integer getAmount()
	{
		return Integer.valueOf(properties.getProperty(KEY_AMOUNT));
	}
	
	public String getMask()
	{
		return properties.getProperty(KEY_MASK);
	}
	
	public String getTemplate()
	{
		return properties.getProperty(KEY_TEMPLATE);
	}
	
	public String getOutputFile()
	{
		return properties.getProperty(KEY_OUTPUT_FILE);
	}
}
