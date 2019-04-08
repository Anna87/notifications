package com.notifications.java.models;


import lombok.*;


@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String title;
    private String autor;
    private Boolean isAvalible;
}
