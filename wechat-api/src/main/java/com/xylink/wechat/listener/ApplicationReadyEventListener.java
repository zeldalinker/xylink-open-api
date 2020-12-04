package com.xylink.wechat.listener;

import com.xylink.wechat.service.ApiReadyService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听spring boot的启动
 * @author Jfei
 *
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>{

	@Resource
	private ApiReadyService apiReadyService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		initCreateDbTable();
	}
	/**
	 * 如果数据库表不存在，则创建表
	 */
	private void initCreateDbTable(){
        apiReadyService.createTableIfNotExist();
	}

}
