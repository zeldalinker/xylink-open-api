package com.xylink.wechat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xylink.wechat.dao.po.WechatConfig;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-04
 */
public interface WechatConfigMapper extends BaseMapper<WechatConfig> {

    @Select("select count(1) from wechat_config")
    int count();

    @Update(" CREATE TABLE wechat_config (" +
            "  xylink_url varchar(100)," +
            "  enterprise_id varchar(32)," +
            "  instance varchar(32)," +
            "  token varchar(32)," +
            "  corp_id varchar(32)," +
            "  corp_secret varchar(100)," +
            "  agent_id  varchar(32)," +
            "  wechat_url varchar(200)," +
            "  wechat_page_url varchar(200)," +
            "  ID varchar(200) NOT NULL PRIMARY KEY " +
            ")" )
    void createTable();


    @Update(" DROP TABLE wechat_config")
    void dropTable();
}
