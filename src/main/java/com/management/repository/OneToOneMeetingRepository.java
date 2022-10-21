package com.management.repository;

import com.management.entity.OneToOneMeeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneToOneMeetingRepository extends CrudRepository<OneToOneMeeting, Integer> {
    OneToOneMeeting findByInitiatorId(Integer id);
    OneToOneMeeting findByParticipantId(Integer id);
}
