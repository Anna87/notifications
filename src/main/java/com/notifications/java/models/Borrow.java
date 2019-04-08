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

//    @OneToOne()
//    @JoinColumn(name="holder_id")
    public Holder holder;

//    @OneToMany(mappedBy = "borrow")
    public List<Book> books;

    public Date expiredDate;

}
