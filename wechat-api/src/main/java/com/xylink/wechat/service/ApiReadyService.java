package com.xylink.wechat.service;

 import com.xylink.wechat.dao.WechatConfigMapper;
import com.xylink.wechat.dao.po.WechatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 import org.springframework.jdbc.BadSqlGrammarException;
 import org.springframework.stereotype.Service;

 import javax.annotation.Resource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-24
 */
@Service
public class ApiReadyService {
    private static final Logger logger = LoggerFactory.getLogger(ApiReadyService.class);

    @Resource
    private WechatConfigMapper wechatConfigMapper;


    public void save(){
        wechatConfigMapper.dropTable();
        wechatConfigMapper.createTable();
        WechatConfig config = new WechatConfig();
        config.setAgentId("1000006");
        config.setCorpSecret("9pmg2HXoere8HuLC_psXPgTuc5EAi2APiBo_aCBiPlQ");
        config.setWechatUrl("https://internal-test.gov.weixin.qq.com/");
        config.setEnterpriseId("default_enterprise");
        config.setInstance("jmrcd");
        config.setToken("123");
        config.setXylinkUrl("https://cloud.xylink.com");
        config.setWechatPageUrl("123123");
        try{
            int latestID = wechatConfigMapper.insert(config);
            System.out.println(latestID);
        }catch (Exception e){
            e.printStackTrace();
        }
        int count  = wechatConfigMapper.count();
        System.out.println(count);
    }


    /**
     * 如果表不存在，则创建表
     */
    public void createTableIfNotExist(){
        try {
            wechatConfigMapper.count();



        } catch (BadSqlGrammarException e) {
            //42X05 表示表或试图不存在
            if("42X05".equals(e.getSQLException().getSQLState())){
                try {
                    wechatConfigMapper.createTable();




                } catch (Exception e2) {
                    logger.error("创建表异常",e2);
                }

            }else{
                logger.error("创建表未知异常",e);
            }
        }catch(Exception e){
            logger.error("创建表未知异常",e);
        }
    }
}
