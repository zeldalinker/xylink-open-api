package com.xylink.wechat.dao;


import com.xylink.wechat.dao.po.MeetingRoom;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;



public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, String>, JpaSpecificationExecutor<MeetingRoom> {
    MeetingRoom findByMeetingId(String meetingId);

    MeetingRoom findFirstByMeetingId(String meetingId);

    MeetingRoom findFirstByMeetingIdAndMeetingUrlIsNotNull(String meetingId);

}