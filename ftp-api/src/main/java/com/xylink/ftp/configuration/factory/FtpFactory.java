package com.xylink.ftp.configuration.factory;

import org.apache.ftpserver.DataConnectionConfiguration;
import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-27
 */
public class FtpFactory {
    private static final Logger logger = LoggerFactory.getLogger(FtpFactory.class);

    private String name;
    private String dir;

    private FtpServer server;

    public FtpFactory(String name,String dir){
        this.name = name;
        this.dir = dir;
    }

    public void init(){
        try {
            DataConnectionConfigurationFactory dccFactory = new DataConnectionConfigurationFactory();
            //主动模式使用的端口
            dccFactory.setActiveLocalPort(7001);
            dccFactory.setPassiveIpCheck(true);
            //被动模式使用的端口范围
            dccFactory.setPassivePorts("7001-7002");
            DataConnectionConfiguration dcc=dccFactory.createDataConnectionConfiguration();

            FtpServerFactory serverFactory = new FtpServerFactory();
            ListenerFactory factory = new ListenerFactory();
            factory.setDataConnectionConfiguration(dcc);
            serverFactory.addListener("default", factory.createListener());
            FtpServer server = serverFactory.createServer();

            BaseUser baseUser = new BaseUser();
            baseUser.setName("anonymous");

            serverFactory.getUserManager().save(baseUser);
            server.start();
            logger.info(" [ ftp service init success ] ");
        }catch (FtpException e){
            logger.info(" [ ftp service init failure ] ",e);
        }
    }



    private void makeFtpDir() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        File dir = new File(path);
        if(!dir.exists()&&!dir.mkdirs()){
            throw new RuntimeException("dir创建异常");
        }
        File ftp = new File(path+"/ftp");
        if(!ftp.exists()&&!ftp.mkdirs()){
            throw new RuntimeException("ftp创建异常");
        }
    }
}
