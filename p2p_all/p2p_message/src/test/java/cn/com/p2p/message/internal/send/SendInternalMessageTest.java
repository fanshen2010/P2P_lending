package cn.com.p2p.message.internal.send;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.exception.SystemException;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SendInternalMessageTest {
	
	@Autowired
	SendInternalMessage sendInternalMessage;
	
	private static final Log logger = LogFactory.getLog(SendInternalMessageTest.class);
	
    @Test
    public void Test(){
    	boolean result =false;
    	try{
    		result = sendInternalMessage.send2FrontUser("6d8e42d9ad534d729c09347f3b5f2a69", "1", "web");
		}catch(SystemException e){
			logger.debug(e.getMessage());
		}
    	assertTrue(result);
    }

}


