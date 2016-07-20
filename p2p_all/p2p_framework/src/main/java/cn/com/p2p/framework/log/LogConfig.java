package cn.com.p2p.framework.log;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;

public class LogConfig {
	
    private String logfullpath;
    
    public void load() throws Exception {
    	if (StringUtils.isNotEmpty(logfullpath)) {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();  
            File externalConfigFile = new File(logfullpath);  
            if(!externalConfigFile.exists()){  
                throw new IOException("Logback External Config File Parameter does not reference a file that exists");  
            }else{  
                if(!externalConfigFile.isFile()){  
                    throw new IOException("Logback External Config File Parameter exists, but does not reference a file");  
                }else{  
                    if(!externalConfigFile.canRead()){  
                        throw new IOException("Logback External Config File exists and is a file, but cannot be read.");  
                    }else{  
                        JoranConfigurator configurator = new JoranConfigurator();  
                        configurator.setContext(lc);  
                        lc.reset();  
                        configurator.doConfigure(logfullpath);  
                        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);  
                    }  
                }     
            } 
    	}

    }

    public String getLogfullpath() {
		return logfullpath;
	}

	public void setLogfullpath(String logfullpath) {
		this.logfullpath = logfullpath;
	}
}
