package com.xylink.wechat.service;

import com.xylink.wechat.bean.wechat.UserInfo;
import com.xylink.wechat.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * 政务微信页面跳转
 *
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Service
public class HomeService {
    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

    @Resource
    private WeChatService weChatService;

    public String toPage(String code) throws BusinessException {
        //获取accessToken
        String token = weChatService.getAccessToken();
        UserInfo userInfo = weChatService.getUserInfo(code,token);
        //获取user_detail
        String indexPage = weChatService.getUserIndexPageUrl(userInfo,token);
        //返回h5_index
        logger.info(" indexPage =  {} ", indexPage);
        return indexPage;
    }
}
