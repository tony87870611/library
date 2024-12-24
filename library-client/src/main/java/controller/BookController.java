package controller;

import org.springframework.web.bind.annotation.RestController;
import service.LibraryApiService;

import javax.annotation.Resource;

@RestController("/book")
public class BookController {

    @Resource
    private LibraryApiService libraryApiService;



}
