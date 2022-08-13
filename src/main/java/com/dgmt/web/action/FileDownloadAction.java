/**
 * Created Date: 4/15/10 7:35 PM
 * Class Name  : FileDownloadAction.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.web.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:35 PM
 * @author
 * @since DGMT 1.0
 */
public class FileDownloadAction extends BaseAction
{
	private String filename;

	private String docType;

	private String inputPath;

	private int did;

	private int docPrjId;

	private boolean sms;

	private int cid;

	private Log logger = LogFactory.getLog(FileDownloadAction.class);

	private static String imageDir;

	static
	{
		DGMTProperties properties = DGMTProperties.getProperties();
		imageDir = properties.getProperty("imagePath","//root//Oracle//Middleware//user_projects//domains//dgmtdomain//DGMTImages//");
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception
	{
		ServletOutputStream out = null;
		FileInputStream inputStream = null;
		try
		
		{
			String fileExt = filename.substring(filename.lastIndexOf("."),filename.length());
			
			if("Questions.txt".equalsIgnoreCase(filename)||"importMarks.xls".equalsIgnoreCase(filename) || ".pdf".equals(fileExt) || ".PDF".equals(fileExt))
			{
				imageDir = servletContext.getRealPath("/")+"/templets/";
			}
			else
			{
				imageDir = servletContext.getRealPath("/")+"/DGMTImages/";
			}
			String filePath = imageDir + filename;

			/*InputStream iStream = new FileInputStream(
					new java.io.File(filePath));*/
			
			
			
			if(".msg".equals(fileExt) || ".MSG".equals(fileExt)){
				docType = "application/msoutlook";
			}else if(".xls".equals(fileExt) || ".XLS".equals(fileExt)){
				docType = "application/vnd.ms-excel";
			}else if(".doc".equals(fileExt) || ".DOC".equals(fileExt)){
				docType = "application/vnd.ms-excel";
			}else if(".jpg".equals(fileExt) || ".JPG".equals(fileExt) || ".jpeg".equals(fileExt) || ".JPEG".equals(fileExt)){
				docType = "image/jpeg";
			}else if(".gif".equals(fileExt) || ".GIF".equals(fileExt)){
				docType = "image/gif";
			}else if(".bmp".equals(fileExt) || ".BMP".equals(fileExt)){
				docType = "image/bmp";
			}else if(".pdf".equals(fileExt) || ".PDF".equals(fileExt)){
				docType = "application/pdf";
			}else if(".doc".equals(fileExt) || ".DOC".equals(fileExt)){
				docType = "application/vnd.ms-excel";
			}else if(".pps".equals(fileExt) || ".PPS".equals(fileExt) || ".ppt".equals(fileExt) || ".PPT".equals(fileExt) || ".thmx".equals(fileExt) || ".THMX".equals(fileExt)){
				docType = "application/vnd.ms-powerpoint";
			}else if(".htm".equals(fileExt) || ".html".equals(fileExt) || ".HTM".equals(fileExt) || ".HTML".equals(fileExt)){
				docType = "text/html";
			}else if(".csv".equals(fileExt) || ".CSV".equals(fileExt)){
				docType = "application/csv";
			}else if(".zip".equals(fileExt) || ".ZIP".equals(fileExt)){
				docType = "application/x-zip-compressed";
			}else if(".rar".equals(fileExt) || ".RAR".equals(fileExt)){
				docType = "application/x-rar-compressed";
			}else if(".txt".equals(fileExt) || ".TXT".equals(fileExt)){
				docType = "text/plain";
			}else if(".chm".equals(fileExt) || ".CHM".equals(fileExt)){
				docType = "application/x-msdownload";
			}else
			{
				docType = "application/octet-stream";
			}
			response.setContentType(docType);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			File f = new File(filePath);
		    String canonicalPath = f.getCanonicalPath();
		    if(!canonicalPath.equals(imageDir + filename)) {
		    		request.getSession().setAttribute("statusval", "invalidData"); 

			}else{
			    inputStream =  new FileInputStream(new java.io.File(filePath));
				
				int c;
	
	            while ((c = inputStream.read()) != -1) {
	            	baos.write(c);
	            }
			}
			response.setContentLength(baos.size());

			/** comment it if u don't want to download pdf */
			response.setHeader("Pragma", "public");
			response.setHeader("Content-Disposition",
					"attachment; filename=\""+ filename +"\"");
			/** get out put Stream */
			out = response.getOutputStream();

			baos.writeTo(out);
			/** Clear OutputStream */
			out.flush();
			out.close();
			
		}
		catch (Exception e)
		{
			logger.error(DGMTUtil.getStackTrace(e));
			addActionError(getText("file.unavailable"));		
		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (Exception e)
				{
					logger.error("Exception: " + DGMTUtil.getStackTrace(e));
				}
			}
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (Exception e)
				{
					logger.error("Exception: " + DGMTUtil.getStackTrace(e));
				}
			}
		}

		return null;
	}

	/**
	 * @return
	 */
	public String getInputPath()
	{
		return inputPath;
	}

	/**
	 * @param value
	 */
	public void setInputPath(String value)
	{
		inputPath = value;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception
	{
		// String rootDir = "d://DGMTDOCS"; // System.getProperty("DOC_ROOT");
		// String projectsDirName = System.getProperty("PROJECTS_DIR_NAME");
		if("Questions.txt".equalsIgnoreCase(filename)||"importMarks.xls".equalsIgnoreCase(filename) || "Help_File_for_Enrollment_and_Examination_vDraft.pdf".equalsIgnoreCase(filename))
		{
			imageDir = servletContext.getRealPath("/")+"/templets/";
		}
		else
		{
			imageDir = servletContext.getRealPath("/")+"/DGMTImages/";
		}
		String filePath = imageDir + filename;

		return new FileInputStream(new java.io.File(filePath));
	}

	/**
	 * @return
	 */
	public String getFilename()
	{
		return "\"" + filename + "\"";
	}

	/**
	 * @param filename
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	/**
	 * @return
	 */
	public int getDid()
	{
		return did;
	}

	/**
	 * @param did
	 */
	public void setDid(int did)
	{
		this.did = did;
	}

	/**
	 * @return
	 */
	public int getDocPrjId()
	{
		return docPrjId;
	}

	/**
	 * @param docPrjId
	 */
	public void setDocPrjId(int docPrjId)
	{
		this.docPrjId = docPrjId;
	}

	/**
	 * @return
	 */
	public boolean isSms()
	{
		return sms;
	}

	/**
	 * @param sms
	 */
	public void setSms(boolean sms)
	{
		this.sms = sms;
	}

	/**
	 * @return
	 */
	public int getCid()
	{
		return cid;
	}

	/**
	 * @param cid
	 */
	public void setCid(int cid)
	{
		this.cid = cid;
	}

	/**
	 * @return
	 */
	public String getDocType()
	{
		return docType;
	}

	/**
	 * @param docType
	 */
	public void setDocType(String docType)
	{
		this.docType = docType;
	}
}
