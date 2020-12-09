package com.xylink.wechat.dao;

import com.xylink.wechat.dao.po.BaseConfig;
import com.xylink.wechat.dao.po.WeChatConfig;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
public interface WeChatConfigRepository extends CrudRepository<BaseConfig, String>, JpaSpecificationExecutor<BaseConfig> {
    BaseConfig findByName(String name);
}
