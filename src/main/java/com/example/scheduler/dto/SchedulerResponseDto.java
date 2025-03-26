package com.example.scheduler.dto;

import com.example.scheduler.entity.Scheduler;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {

    private Long id;
    private String title;
    private String contents;


    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();

    }
}
