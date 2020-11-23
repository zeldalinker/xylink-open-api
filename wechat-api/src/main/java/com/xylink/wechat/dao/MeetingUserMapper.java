package com.xylink.wechat.dao;

import com.xylink.wechat.dao.po.MeetingUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Mapper
public interface MeetingUserMapper {

    @Select(value = "select * from lanxin_meeting_user where user_phone = #{phone}")
    List<MeetingUser> selectByPhone(String phone);


}
