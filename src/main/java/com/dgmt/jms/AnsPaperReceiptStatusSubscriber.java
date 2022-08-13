/**
 * Created Date: 12/8/10 12:09 PM
 * Class Name  : AnsweredPapersStatusSubscriber.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.dgmt.dto.StatusMessageDTO;
import com.dgmt.service.api.MessagingService;
import com.dgmt.util.DGMTUtil;
import com.dgmt.util.Log;
import com.dgmt.util.LogFactory;
import com.dgmt.web.ServiceLocator;

/**
 * Description:
 * 
 * @version 1.0 12/8/10 12:09 PM
 * @author CTE
 * @since DGMT 1.0
 */
public class AnsPaperReceiptStatusSubscriber implements MessageListener
{
	
	private Log logger = LogFactory.getLog(AnsPaperReceiptStatusSubscriber.class);
	
	/**
	 * @param message
	 */
	@Override
	public void onMessage(Message message)
	{
		logger.info("onMessage starts");
		
		MessagingService service = ServiceLocator.getInstance()
				.getMessagingService();

		try
		{
			if (message instanceof ObjectMessage)
			{
				StatusMessageDTO messageDTO = (StatusMessageDTO) ((ObjectMessage) message)
						.getObject();

				try
				{
					service.updateMessageReceipt(messageDTO);
				}
				catch (Exception e)
				{
					logger.error(DGMTUtil.getStackTrace(e));
				}
			}
		}
		catch (JMSException ex)
		{
			logger.error(DGMTUtil.getStackTrace(ex));
		}
		catch (Exception e)
		{
			logger.error(DGMTUtil.getStackTrace(e));
		}
		
		logger.info("onMessage ends");
	}
}
