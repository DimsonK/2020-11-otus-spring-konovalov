package ru.otus.spring.homework.spring04.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring04.exceptions.BadResourceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ResourceReaderCsv implements ResourceReader {

    public List<String[]> getResource(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName))))) {
            // инициализация списка
            List<String[]> res = new ArrayList<>();
            // чтение первой строки
            String line = reader.readLine();
            // последовательное чтение строк из csv
            while (line != null) {
                String[] attr = line.split(",");
                res.add(attr);
                line = reader.readLine();
            }
            return res;
        } catch (IOException e) {
            throw new BadResourceException(String.format("Error reading resource: %s", fileName), e);
        }
    }
}
