package com.notifications.java.services;


import com.google.common.collect.Lists;
import com.notifications.java.Dto.BorrowDto;
import com.notifications.java.models.Borrow;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowService {

    public Borrow ConvertFromDto(BorrowDto dto) {
        return Borrow.builder().books(Lists.newArrayList(dto.getBooks())).holder(dto.getHolder()).expiredDate(new Date(dto.getExpiredDate())).build();
    }

}
