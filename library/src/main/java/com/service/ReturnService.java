package com.service;

import com.utils.ValidationException;

import java.awt.print.Book;

public interface ReturnService {

    void returnBook(String bookId) throws ValidationException;
}
