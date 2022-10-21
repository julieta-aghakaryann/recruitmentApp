package com.management.controller;

import com.management.dto.OneToOneMeetingDto;
import com.management.entity.OneToOneMeeting;
import com.management.service.OneToOneMeetingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/company/meeting")
public class OneToOneMeetingController {

    public final OneToOneMeetingService oneToOneMeetingService;

    public OneToOneMeetingController(OneToOneMeetingService oneToOneMeetingService) {
        this.oneToOneMeetingService = oneToOneMeetingService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMeeting(@RequestBody OneToOneMeetingDto dto) {
        return ResponseEntity.ok().body(oneToOneMeetingService.createMeeting(dto));
    }

    @GetMapping("/getAllMeetings")
    public List<OneToOneMeeting> getAllMeetings() {
        return oneToOneMeetingService.getAllMeetings();
    }

    @GetMapping("/getByInitiator/{initiatorId}")
    public ResponseEntity<?> getMeetingByInitiatorId(@PathVariable("initiatorId") Integer initiatorId) {
        return ResponseEntity.ok().body(oneToOneMeetingService.getMeetingByInitiatorId(initiatorId));
    }

    @GetMapping("/getByParticipant/{participantId}")
    public ResponseEntity<?> getMeetingByParticipantId(@PathVariable("participantId") Integer participantId) {
        return ResponseEntity.ok().body(oneToOneMeetingService.getMeetingBParticipantId(participantId));
    }

    @GetMapping("/updateDate")
    public ResponseEntity<?> updateDate(@RequestParam Integer id,
                                        @RequestParam("startDate")
                                        @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate) {
        return ResponseEntity.ok().body(oneToOneMeetingService.updateDate(id, startDate));
    }
}
