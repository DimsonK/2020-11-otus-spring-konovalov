package ru.otus.spring.homework.service;

import java.util.List;

public interface ResourceReader {

    List<String[]> getResource(String fileName);

}
