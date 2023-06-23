package com.ghj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 86187
 */

@Data
public class User {
    private Long id;
    private String name;
    private int age;
    private LocalDateTime local_date_time;
}
