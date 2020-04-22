package com.javacourse.stormnetbot.shared.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long chatId;
    private String username;
    private String status;

}
