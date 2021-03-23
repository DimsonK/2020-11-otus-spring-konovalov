package ru.otus.spring.homework.spring15.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
@Service
public class RandomService {

    public String getRandomAlNumber(int length) {
        try {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = length == 0 ? 10 : length;
            Random random = SecureRandom.getInstanceStrong();

            return random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Integer getRandomInt(int min, int max) {
        try {
            Random random = SecureRandom.getInstanceStrong();
            return random.nextInt(max - min) + min;
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        return 0;
    }


}
