/**
 * Created Date: 12/16/10 2:49 PM
 * Class Name  : EditedImagefilter.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Description:
 * 
 * @version 1.0 12/16/10 2:49 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class EditedImagefilter implements FilenameFilter
{
	private String filterString;

	/**
	 * Creates a new EditedImagefilter object.
	 * 
	 * @param qppId
	 */
	public EditedImagefilter(String qppId)
	{
		this.filterString = "_" + qppId + "_";
	}

	/**
	 * @param dir
	 * @param name
	 * @return
	 */
	@Override
	public boolean accept(File dir, String name)
	{
		// TODO Auto-generated method stub
		return name.contains(filterString);
	}
}
