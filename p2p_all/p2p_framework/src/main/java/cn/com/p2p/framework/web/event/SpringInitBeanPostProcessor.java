package cn.com.p2p.framework.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class SpringInitBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	cn.com.p2p.framework.log.LogConfig logConfig;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			logConfig.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
