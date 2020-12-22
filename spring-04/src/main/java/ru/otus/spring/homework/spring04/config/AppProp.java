package ru.otus.spring.homework.spring04.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "application")
public class AppProp {
    private AppLang lang;
    private int passPercent;
    private Map<String, TestResource> store;

    public AppLang getLang() {
        return lang;
    }

    public void setLang(AppLang lang) {
        this.lang = lang;
    }

    public int getPassPercent() {
        return passPercent;
    }

    public void setPassPercent(int passPercent) {
        this.passPercent = passPercent;
    }

    public Map<String, TestResource> getStore() {
        return store;
    }

    public void setStore(Map<String, TestResource> store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "AppProp{" +
                "lang=" + lang +
                ", passPercent=" + passPercent +
                ", store=" + store +
                '}';
    }

    public static class AppLang {
        private Locale defLang;
        private boolean useSystemLang;

        public Locale getDefLang() {
            return defLang;
        }

        public void setDefLang(Locale defLang) {
            this.defLang = defLang;
        }

        public boolean isUseSystemLang() {
            return useSystemLang;
        }

        public void setUseSystemLang(boolean useSystemLang) {
            this.useSystemLang = useSystemLang;
        }

        @Override
        public String toString() {
            return "AppLang{" +
                    "defLang='" + defLang + '\'' +
                    ", useSystemLang=" + useSystemLang +
                    '}';
        }
    }

    public static class TestResource {
        private String questions;
        private String answers;

        public String getQuestions() {
            return questions;
        }

        public void setQuestions(String questions) {
            this.questions = questions;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

        @Override
        public String toString() {
            return "TestResource{" +
                    "questions='" + questions + '\'' +
                    ", answers='" + answers + '\'' +
                    '}';
        }
    }

}
