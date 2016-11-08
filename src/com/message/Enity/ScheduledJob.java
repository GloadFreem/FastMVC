package com.message.Enity;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.message.manager.MessageMananger;

public class ScheduledJob extends QuartzJobBean{  
    
    private AnotherBean anotherBean;   
    
       
    @Override  
    protected void executeInternal(JobExecutionContext arg0)  
            throws JobExecutionException {  
		System.out.println();
    	System.out.println("---����");
        anotherBean.printAnotherMessage();  
        

    }  
   
	public AnotherBean getAnotherBean() {
		return anotherBean;
	}

	public void setAnotherBean(AnotherBean anotherBean) {  
        this.anotherBean = anotherBean;  
    }  
} 
