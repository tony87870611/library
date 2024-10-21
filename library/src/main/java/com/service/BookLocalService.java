package com.service;

import com.dto.*;
import com.utils.ValidationException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface BookLocalService {

    void createBook(CreateBookRequestDto requestDto) throws ValidationException;

    void updateBook(UpdateBookRequestDto requestDto) throws ValidationException;

    Pair<Integer, List<BookDto>> queryBook(QueryBookRequestDto requestDto);
}
