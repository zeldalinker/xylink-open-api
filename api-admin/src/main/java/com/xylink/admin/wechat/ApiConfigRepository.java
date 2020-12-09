package com.xylink.admin.wechat;

import com.xylink.admin.wechat.po.BaseConfig;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
public interface ApiConfigRepository extends CrudRepository<BaseConfig, String>, JpaSpecificationExecutor<BaseConfig> {
}
