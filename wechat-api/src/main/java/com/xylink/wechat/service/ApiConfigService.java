package com.xylink.wechat.service;

import com.xylink.wechat.dao.WeChatConfigRepository;
import com.xylink.wechat.dao.po.WeChatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-24
 */
@Service
public class ApiConfigService {
    @Resource
    private WeChatConfigRepository weChatConfigRepository;





}
