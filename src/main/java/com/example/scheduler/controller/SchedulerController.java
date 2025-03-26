package com.example.scheduler.controller;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    private final Map<Long, Scheduler> schedulerList = new HashMap<>();

    @PostMapping
    public ResponseEntity<SchedulerResponseDto> createScheduler(@RequestBody SchedulerRequestDto dto) {

        // 식별자가 1씩 증가 하도록 만듦
        Long schedulerId = schedulerList.isEmpty() ? 1 : Collections.max(schedulerList.keySet()) +1;

        // 요청받은  Data로 Scheduler 객체 생성
        Scheduler scheduler = new Scheduler(schedulerId, dto.getTitle(), dto.getContents());

        // Inmemory Database에 Scheduler 저장
        schedulerList.put(schedulerId, scheduler);

        return new ResponseEntity<>(new SchedulerResponseDto(scheduler), HttpStatus.CREATED);
    }

    @GetMapping
    public List<SchedulerResponseDto> findAllScheduler() {

        // init list
        List<SchedulerResponseDto> responseList = new ArrayList<>();

        // HsshMap<> -> List<MemoResponseDto>
        for (Scheduler scheduler : schedulerList.values()) {
            SchedulerResponseDto responseDto = new SchedulerResponseDto(scheduler);
            responseList.add(responseDto);
        }


        return responseList;
    }

    @GetMapping("/{id}")
    public SchedulerResponseDto findSchedulerById(@PathVariable Long id) {

        Scheduler scheduler = schedulerList.get(id);

        return new SchedulerResponseDto(scheduler);
    }
    @PutMapping("/{id}")
    public SchedulerResponseDto updateSchedulerById (
            @PathVariable Long id,
            @RequestBody SchedulerRequestDto dto
    ) {
        Scheduler scheduler = schedulerList.get(id);

        scheduler.update(dto);

        return new SchedulerResponseDto(scheduler);
    }
    @DeleteMapping("/{id}")
    public void deleteScheduler(@PathVariable Long id) {

        schedulerList.remove(id);

    }

}