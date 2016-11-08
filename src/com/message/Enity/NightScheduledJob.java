package com.message.Enity;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jinzht.tools.HttpUtil;


public class NightScheduledJob extends QuartzJobBean{  
    
    private NightBean anotherBean;   
       
    @Override  
    protected void executeInternal(JobExecutionContext arg0)  
            throws JobExecutionException {  
    	System.out.println("---����ɾ������:"+HttpUtil.getDateTime());
        anotherBean.printNightMessage();  
    }  
   
    public void setAnotherBean(NightBean anotherBean) {  
        this.anotherBean = anotherBean;  
    }

	public NightBean getAnotherBean() {
		return anotherBean;
	}  
    
    
} 
