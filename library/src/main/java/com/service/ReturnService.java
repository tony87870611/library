package com.service;

import com.utils.ValidationException;

public interface ReturnService {

    void returnBook(String bookId) throws ValidationException;
}
