package com.xylink.wechat.dao;

import com.xylink.wechat.dao.po.MeetingRoom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-19
 */
@Mapper
public interface MeetingRoomMapper {
    @Select(value = "select * from lanxin_meeting_room where meeting_id = #{meetingId} and end_time > #{latestMillis} and meeting_url is not null order by start_time asc")
    List<MeetingRoom> selectExpiryRooms(@Param("meetingId") String meetingId, @Param("latestMillis") long latestMillis);

    @Insert(value = "insert lanxin_meeting_room ('meeting_id','meeting_room_number','user_phone','user_display_name','title','start_time','end_time','meeting_url')" +
            " values (#{meetingId},#{meetingRoomNumber},#{userPhone},#{userDisplayName},#{title}),#{startTime},#{endTime}) ")
    MeetingRoom save(MeetingRoom room);

    @Select(value = "select * from lanxin_meeting_room where meeting_id = #{meetingId} and meeting_url != null order by start_time asc")
    MeetingRoom selectInfoById(String meetingId);



}
