/**
 * Created Date: 4/15/10 7:33 PM
 * Class Name  : DGMTUtil.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:33 PM
 * @author
 * @since DGMT 1.0
 */
public class DGMTUtil
{
	/**
	 * @param s
	 * @return
	 */
	private static Log logger = LogFactory.getLog(DGMTUtil.class);

	/**
	 * @param s
	 * @return
	 */
	public static String StringArrayToString(String[] s)
	{
		String tmp = "";

		for (String str : s)
		{
			tmp += (str + ",");
		}

		tmp = tmp.substring(0, tmp.length() - 1);

		return tmp;
	}

	/** Read an input stream in its entirety into a byte array */
	public static byte[] readInputStream(InputStream inputStream)
			throws IOException
	{
		int bufSize = 1024 * 1024;
		byte[] content;

		List<byte[]> parts = new LinkedList();
		InputStream in = new BufferedInputStream(inputStream);

		byte[] readBuffer = new byte[bufSize];
		byte[] part = null;
		int bytesRead = 0;

		// read everyting into a list of byte arrays
		while ((bytesRead = in.read(readBuffer, 0, bufSize)) != -1)
		{
			part = new byte[bytesRead];
			System.arraycopy(readBuffer, 0, part, 0, bytesRead);
			parts.add(part);
		}

		// calculate the total size
		int totalSize = 0;

		for (byte[] partBuffer : parts)
		{
			totalSize += partBuffer.length;
		}

		// allocate the array
		content = new byte[totalSize];

		int offset = 0;

		for (byte[] partBuffer : parts)
		{
			System.arraycopy(partBuffer, 0, content, offset, partBuffer.length);
			offset += partBuffer.length;
		}

		return content;
	}

	/**
	 * @param buf
	 * @return
	 */
	public static String getFileType(byte[] buf)
	{
		boolean isText = true;

		for (int i = 0; i < 10; i++)
		{
			int c = buf[i] & 255;

			if (((c < 32) && (c != 9) && (c != 10) && (c != 13)) || (c > 126))
			{
				isText = false;

				break;
			}
		}

		if (isText)
		{
			return "txt"; // TEXT
		}
		else
		{
			return "NONE";
		}
	}

	/**
	 * @param buf
	 * @return
	 */
	public static boolean isImage(byte[] buf)
	{
		// lwf added by vmunukutla
		int b0 = buf[0] & 255;

		// lwf added by vmunukutla
		int b1 = buf[1] & 255;

		// lwf added by vmunukutla
		int b2 = buf[2] & 255;

		// lwf added by vmunukutla
		int b3 = buf[3] & 255;

		// Combined TIFF and DICOM created by GE Senographe scanners
		boolean isImage = false;

		if ((buf[128] == 68) && (buf[129] == 73) && (buf[130] == 67)
				&& (buf[131] == 77)
				&& (((b0 == 73) && (b1 == 73)) || ((b0 == 77) && (b1 == 77))))
		{
			isImage = true; // TIFF_AND_DICOM
		}
		else
		// Big-endian TIFF ("MM")
		if ((b0 == 73) && (b1 == 73) && (b2 == 42) && (b3 == 0))
		{
			isImage = true;
		}
		else
		// Little-endian TIFF ("II")
		if ((b0 == 77) && (b1 == 77) && (b2 == 0) && (b3 == 42))
		{
			isImage = true;
		}
		else
		// JPEG
		if ((b0 == 255) && (b1 == 216) && (b2 == 255))
		{
			isImage = true;
		}
		else
		// GIF ("GIF8")
		if ((b0 == 71) && (b1 == 73) && (b2 == 70) && (b3 == 56))
		{
			isImage = true;
		}
		else
		// DICOM ("DICM" at offset 128)
		if ((buf[128] == 68) && (buf[129] == 73) && (buf[130] == 67)
				&& (buf[131] == 77))
		{
			isImage = true;
		}
		else
		// ACR/NEMA with first tag = 00002,00xx or 00008,00xx
		if (((b0 == 8) || (b0 == 2)) && (b1 == 0) && (b3 == 0))
		{
			isImage = true; // DICOM
		}
		else
		// PNG
		if ((b0 == 137) && (b1 == 80) && (b2 == 78) && (b3 == 71))
		{
			return true;
		}
		else if (((b0 == 66) && (b1 == 77)))
		{
			isImage = true;
		}

		if (isImage)
		{
			return true;
		}

		return false;
	}
	
	/**
	 * To get full stacktrace
	 * 
	 * @param aThrowable
	 * @return
	 */
	public static String getStackTrace(Exception e)
	{
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		e.printStackTrace(printWriter);
		return result.toString();
	}
	
	/**
	 * @param ipAddress
	 * @return
	 */
	public static boolean isIpAddressPinging(String ipAddress)
	{		
		logger.info("Ping Poller Starts...");

		boolean status = false;

		try
		{
			InetAddress inet = InetAddress.getByName(ipAddress);
			
			logger.info("Sending Ping Request to " + ipAddress);
			status = inet.isReachable(5000); // Timeout = 5000 milli seconds

			if (status)
			{				
				logger.debug("Status : Host is reachable");
			}
			else
			{			
				logger.debug("Status : Host is not reachable");
			}
		}
		catch (UnknownHostException e)
		{			
			logger.error("Host does not exists");
		}
		catch (IOException e)
		{			
			logger.error("Error in reaching the Host");
		}

		return status;
	}
}
