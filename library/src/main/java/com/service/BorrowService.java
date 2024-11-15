package com.service;

import com.dto.BorrowBookRequestDto;
import com.utils.ValidationException;

public interface BorrowService {

    void borrowBook(BorrowBookRequestDto requestDto) throws ValidationException;
}
