package com.dgmt.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class DGMTProperties extends Properties
{
	private static Hashtable<String, String> valueCache = null;

	private static Log logger = LogFactory.getLog(DGMTProperties.class);

	private static DGMTProperties instance = new DGMTProperties(); // singleton

	static
	{
		Properties props = new Properties();
		File f = new File("");
		String filePath = f.getAbsolutePath() + File.separator + "config"
				+ File.separator;
		String propsFile = filePath + "weblogic.properties";
		try
		{
			props.load(new FileInputStream(propsFile));
			valueCache = new Hashtable<String, String>();

			Enumeration keys = props.keys();

			while (keys.hasMoreElements())
			{
				String key = (String) keys.nextElement();
				String value = props.getProperty(key);

				if (value != null)
				{
					value = value.trim();
				}

				valueCache.put(key, value);
			}
		}
		catch (Exception ex)
		{
			logger.error(DGMTUtil.getStackTrace(ex));
		}

	}

	/**
	 * Creates a new DGMTProperties object.
	 */
	private DGMTProperties()
	{
	}

	/**
	 * @return
	 */
	public static DGMTProperties getProperties()
	{
		return instance;
	}

	/**
	 * @param key
	 * @param defVal
	 * @return
	 */
	@Override
	public String getProperty(String key, String defVal)
	{
		if (valueCache == null)
		{
			return defVal;
		}

		String val = getProperty(key);

		if (val == null)
		{
			val = defVal;
		}

		return val;
	}

	/**
	 * @param keyPattern
	 * @return
	 */
	public Map<String, String> getProperties(String keyPattern)
	{
		if (valueCache == null)
		{
			return null;
		}

		Map<String, String> keyMap = new HashMap<String, String>();

		for (String key : valueCache.keySet())
		{
			if (key.startsWith(keyPattern))
			{
				String val = getProperty(key);

				if (val == null)
				{
					throw new IllegalArgumentException(key);
				}

				keyMap.put(key, val);
			}
		}

		return keyMap;
	}

	/**
	 * @param key
	 * @param defVal
	 * @return
	 */
	public boolean getProperty(String key, boolean defVal)
	{
		if (valueCache == null)
		{
			return defVal;
		}

		String val = getProperty(key);

		if (val == null)
		{

			return defVal;
		}

		return "true".equalsIgnoreCase(val);
	}

	/**
	 * @param key
	 * @param defVal
	 * @return
	 */
	public int getProperty(String key, int defVal)
	{
		if (valueCache == null)
		{
			return defVal;
		}

		String val = getProperty(key);

		if (val == null)
		{

			return defVal;
		}

		return Integer.parseInt(val);
	}

	/**
	 * @param key
	 * @return
	 */
	@Override
	public String getProperty(String key)
	{
		if (valueCache == null)
		{
			return null;
		}

		String val = valueCache.get(key);

		if ((val == null) || (val.length() == 0))
		{
			val = null;
		}

		return val; // already trimmed during initialization
	}
}
