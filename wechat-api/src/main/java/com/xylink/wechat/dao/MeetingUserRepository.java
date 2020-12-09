package com.xylink.wechat.dao;

 import com.xylink.wechat.dao.po.MeetingUser;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.stereotype.Repository;

 import java.util.List;

public interface MeetingUserRepository extends CrudRepository<MeetingUser, String>, JpaSpecificationExecutor<MeetingUser> {
    List<MeetingUser> findByUserPhone(String userPhone);
}
