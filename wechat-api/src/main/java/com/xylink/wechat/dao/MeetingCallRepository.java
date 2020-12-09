package com.xylink.wechat.dao;

import com.xylink.wechat.dao.po.MeetingCall;
import com.xylink.wechat.dao.po.MeetingUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MeetingCallRepository extends CrudRepository<MeetingCall, String>, JpaSpecificationExecutor<MeetingCall> {

    List<MeetingCall> findAllByUserPhoneOrderByCallTimeDesc(String userPhone);
    MeetingCall findFirstByCallNumberAndUserPhone(String callNumber, String userPhone);
}