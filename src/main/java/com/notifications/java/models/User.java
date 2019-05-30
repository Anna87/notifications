package com.notifications.java.models;

import lombok.*;

import java.util.Set;


@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    private boolean enabled;
}
