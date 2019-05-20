package com.notifications.java.models;


import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Holder {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
