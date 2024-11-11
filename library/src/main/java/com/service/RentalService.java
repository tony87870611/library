package com.service;

import com.utils.ValidationException;

public interface RentalService {

    void borrowBook(String bookId) throws ValidationException;
}
