package com.management.service;

import com.management.dto.OneToOneMeetingDto;
import com.management.dto.response.OneToOneMeetingResponseDto;
import com.management.entity.OneToOneMeeting;

import java.time.LocalDate;
import java.util.List;

public interface OneToOneMeetingService {
    OneToOneMeeting createMeeting(OneToOneMeetingDto dto);
    List<OneToOneMeeting> getAllMeetings();
    OneToOneMeetingResponseDto getMeetingByInitiatorId(Integer id);
    OneToOneMeetingResponseDto getMeetingBParticipantId(Integer id);
    OneToOneMeeting updateDate(Integer initiatorId, LocalDate startDate);
}
