package com.notifications.java.Dto;

import com.notifications.java.models.Book;
import com.notifications.java.models.Holder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
    String id;
    Holder holder;
    Book[] books;
    long expiredDate;
}
