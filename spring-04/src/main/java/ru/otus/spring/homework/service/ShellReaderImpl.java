package ru.otus.spring.homework.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ShellReaderImpl implements ShellReader {

    private final Scanner scanner = new Scanner(System.in);

    public String readShell() {
        return scanner.next();
    }

}
