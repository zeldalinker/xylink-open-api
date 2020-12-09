package com.xylink.wechat.dao.po;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-03
 */
@Entity
@Table(name = "wechat_config")
@Data
public class BaseConfig extends BaseModel {
    @Column(name = "name")
    private String name;
    @Column(name = "context",columnDefinition="clob")
    private String context;

}
