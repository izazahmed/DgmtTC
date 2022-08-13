/**
 * Created Date: 12/8/10 12:12 PM
 * Class Name  : SampleSubscriber.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.jms;

import java.io.File;
import java.io.FileOutputStream;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.dgmt.dto.TestCenterMessageDTO;
import com.dgmt.service.api.MessagingService;
import com.dgmt.util.DGMTProperties;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
import com.dgmt.web.ServiceLocator;

/**
 * Description:
 * 
 * @version 1.0 12/8/10 12:12 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class TCDataSubscriber implements MessageListener
{
	private Log logger = LogFactory.getLog(TCDataSubscriber.class);

	/**
	 * @param message
	 */
	@Override
	public void onMessage(Message message)
	{
		logger.info("Received message from tc data topic.");

		MessagingService service = ServiceLocator.getInstance()
				.getMessagingService();

		try
		{
			if (message instanceof ObjectMessage)
			{
				TestCenterMessageDTO messageDTO = (TestCenterMessageDTO) ((ObjectMessage) message)
						.getObject();

				try
				{
					// save exam data
					service.saveExamData(messageDTO);

					// send status message
					service.sendStatusMessage(message.getJMSCorrelationID());
				}
				catch (Exception e)
				{
					logger.error(DGMTUtil.getStackTrace(e));
					throw new RuntimeException(e);
				}
			}
			else if (message instanceof BytesMessage)
			{
				int size = message.getIntProperty("size");
				String name = message.getStringProperty("name");
				String imageLoc = DGMTProperties.getProperties().getProperty("TC_IMAGE_LOCATION");
				
				if (size > 0)
				{
					// allocate a buffer to store the file contents
					
					byte[] replyBytes = new byte[size];
					((BytesMessage) message).readBytes(replyBytes);
					
					File f = new File(imageLoc+ name);
					String canonicalPath = f.getCanonicalPath();
					 
					if (!canonicalPath.equals(imageLoc + name)) { // Validation
						throw new IllegalArgumentException();
					} else {
						FileOutputStream outFile = new FileOutputStream(f);
						outFile.write(replyBytes);
						outFile.close();
					}
				}

				// send status message
				// service.sendStatusMessage(message.getJMSCorrelationID());

			}
			else if(message instanceof TextMessage)
			{
				String examId = message.getStringProperty("examId");
				
				String ewId = service.getActiveExamWindow(examId) == null ? null: service.getActiveExamWindow(examId).getId().toString();
				
				if(ewId != null)
				{
					String text = ((TextMessage) message).getText();
					if(text.equalsIgnoreCase("DELETE"))
					{
						service.deleteExamData(examId);
					}
					
					service.sendDeleteNotification(ewId);
				}
			}
		}
		catch (JMSException ex)
		{
			logger.error(DGMTUtil.getStackTrace(ex));
			throw new RuntimeException(ex);
		}
		catch (Exception e)
		{
			logger.error(DGMTUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
}
