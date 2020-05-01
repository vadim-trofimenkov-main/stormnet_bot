package com.javacourse.stormnetbot.shared.entity;


import com.javacourse.stormnetbot.shared.entity.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long chatId;
    private String username;
    private String status;
    private Role role;

    public User(Long chatId, String username, String status) {
        this.chatId = chatId;
        this.username = username;
        this.status = status;
        role = Role.getByName(status);
    }

    public Role getRole() {
        if (role == null) {
            role = Role.getByName(status);
        }
        return role;
    }
}
