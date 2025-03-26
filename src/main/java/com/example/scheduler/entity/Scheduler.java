package com.example.scheduler.entity;

import com.example.scheduler.dto.SchedulerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Scheduler {

    private Long id;
    private String title;
    private String contents;


    public void update(SchedulerRequestDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
