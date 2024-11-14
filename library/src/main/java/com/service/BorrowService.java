package com.service;

import com.utils.ValidationException;

public interface BorrowService {

    void borrowBook(String bookId) throws ValidationException;
}
