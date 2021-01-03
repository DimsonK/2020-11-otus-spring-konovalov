package ru.otus.spring.homework.spring06.shell;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ShellReaderImpl implements ShellReader {
    private final Scanner scanner = new Scanner(System.in);

    public String readShell() {
        return scanner.nextLine();
    }

}
