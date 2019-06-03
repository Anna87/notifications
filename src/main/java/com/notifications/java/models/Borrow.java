package com.notifications.java.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder(toBuilder = true)
@Setter
@Getter
public class Borrow {
    private String id;
    public Holder holder;
    public List<Book> books;
    public Date expiredDate;

}
