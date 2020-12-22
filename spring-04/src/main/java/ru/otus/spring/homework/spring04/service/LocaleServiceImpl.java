package ru.otus.spring.homework.spring04.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring04.config.AppProp;

import java.util.Locale;

@Service
public class LocaleServiceImpl implements LocaleService {
    private final AppProp prop;

    public LocaleServiceImpl(AppProp prop) {
        this.prop = prop;
    }

    public Locale getLocale() {
        Locale lang;
        if (prop.getLang().isUseSystemLang()) {
            lang = getSystemLocale();
        } else {
            lang = prop.getLang().getDefLang();
        }
        return lang;
    }

    private Locale getSystemLocale() {
        return new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
    }
}
