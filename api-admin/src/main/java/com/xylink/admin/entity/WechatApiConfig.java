package com.xylink.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 林骏
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wechat_api_config")
public class WechatApiConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String name;
    @TableField("corp_id")
    private String corpId;
    @TableField("corp_secret")
    private String corpSecret;
    @TableField("agent_id")
    private String agentId;
    @TableField("wechat_url")
    private String wechatUrl;
    @TableField("instance")
    private String instance;
    @TableField("enterprise_id")
    private String enterpriseId;
    @TableField("xy_url")
    private String xyUrl;
    @TableField("token")
    private String token;
    @TableField("host")
    private String host;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
