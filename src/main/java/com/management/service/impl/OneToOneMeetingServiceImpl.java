package com.management.service.impl;

import com.management.dto.OneToOneMeetingDto;
import com.management.dto.response.OneToOneMeetingResponseDto;
import com.management.entity.OneToOneMeeting;
import com.management.repository.OneToOneMeetingRepository;
import com.management.repository.UserRepository;
import com.management.service.OneToOneMeetingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class OneToOneMeetingServiceImpl implements OneToOneMeetingService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final OneToOneMeetingRepository oneToOneMeetingRepository;
    private final UserRepository userRepository;

    public OneToOneMeetingServiceImpl(OneToOneMeetingRepository oneToOneMeetingRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.oneToOneMeetingRepository = oneToOneMeetingRepository;
    }

    @Override
    @Transactional
    public OneToOneMeeting createMeeting(OneToOneMeetingDto dto) {
        OneToOneMeeting meeting = modelMapper.map(dto, OneToOneMeeting.class);
        return oneToOneMeetingRepository.save(meeting);
    }

    @Override
    @Transactional
    public List<OneToOneMeeting> getAllMeetings() {
        return (List<OneToOneMeeting>) oneToOneMeetingRepository.findAll();
    }

    @Override
    @Transactional
    public OneToOneMeetingResponseDto getMeetingByInitiatorId(Integer id) {
        OneToOneMeeting byInitiatorId = oneToOneMeetingRepository.findByInitiatorId(id);
        var initiator = userRepository.findById(id).orElse(null);
        var participant = userRepository.findById(byInitiatorId.getParticipantId()).orElse(null);
        return new OneToOneMeetingResponseDto(initiator, participant, byInitiatorId.getDate(), byInitiatorId.getSubject(), byInitiatorId.getDescription());
    }

    @Override
    @Transactional
    public OneToOneMeetingResponseDto getMeetingBParticipantId(Integer id) {
        OneToOneMeeting byParticipantId = oneToOneMeetingRepository.findByParticipantId(id);
        var participant = userRepository.findById(id).orElse(null);
        var initiator = userRepository.findById(byParticipantId.getInitiatorId()).orElse(null);
        return new OneToOneMeetingResponseDto(initiator, participant, byParticipantId.getDate(), byParticipantId.getSubject(), byParticipantId.getDescription());
    }

    @Override
    @Transactional
    public OneToOneMeeting updateDate(Integer initiatorId, LocalDate startDate) {
        OneToOneMeeting byInitiatorId = oneToOneMeetingRepository.findByInitiatorId(initiatorId);
        byInitiatorId.setDate(startDate);
        oneToOneMeetingRepository.save(byInitiatorId);
        return byInitiatorId;
    }
}
