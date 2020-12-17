package ru.otus.spring.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.config.AppProp;
import ru.otus.spring.homework.domain.Answer;
import ru.otus.spring.homework.domain.Person;
import ru.otus.spring.homework.domain.Question;
import ru.otus.spring.homework.domain.TestResult;

import java.util.List;

@Service
public class StudentTestServiceImpl implements StudentTestService {
    private static final Logger log = LoggerFactory.getLogger(StudentTestServiceImpl.class);

    private final MessageSource messageSource;
    private final LocaleService localeService;
    private final CsvFileReader reader;
    private final AppProp prop;
    private final ShellReader shellReader;

    public StudentTestServiceImpl(
            MessageSource messageSource,
            LocaleService localeService,
            CsvFileReader reader,
            AppProp prop,
            ShellReader shellReader
    ) {
        this.messageSource = messageSource;
        this.localeService = localeService;
        this.reader = reader;
        this.prop = prop;
        this.shellReader = shellReader;
    }

    /**
     * Тестирование студента
     * @param person    студент
     * @param questions вопросы с вариантами ответов
     */
    private void studentTest(Person person, List<Question> questions) {
        questions.forEach(question -> {
            System.out.println(question.getId() + ". " + question.getName());
            List<Answer> answers = question.getAnswerList();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + answers.get(i).getName());
            }
            System.out.print(messageSource.getMessage("enter.answer", null, localeService.getLocale()));
            int answer = checkIntEnter(0, answers.size());
            int answerId = answer - 1;
            person.addTestResult(new TestResult(
                    question.getId(),
                    answers.get(answerId).getId(),
                    answers.get(answerId).isCorrect()));
        });
    }

    /**
     * Проверка ввода
     * @param min минимальное допустимое значение
     * @param max максимальное допустимое значение
     * @return введенное значение
     */
    private int checkIntEnter(int min, int max) {
        int answer;
        while (true) {
            try {
                answer = Integer.parseInt(shellReader.readShell());
                if (answer <= 0 || answer < min || answer > max) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException nfe) {
                System.out.print(messageSource.getMessage("enter.error", null, localeService.getLocale()));
            }
        }
        return answer;
    }

    /**
     * Регистрация студента
     * @return Person
     */
    @Override
    public Person register() {
        System.out.println(messageSource.getMessage("welcome.to.test", null, localeService.getLocale()));
        System.out.print(messageSource.getMessage("enter.firstName", null, localeService.getLocale()));
        String personFirstName = shellReader.readShell();
        System.out.print(messageSource.getMessage("enter.lastName", null, localeService.getLocale()));
        String personLastName = shellReader.readShell();
        return new Person(personFirstName, personLastName);
    }

    /**
     * Запускает тестирование студента
     * Результат теста сохраняется в экземпляре класса Person и отображается на экране
     */
    @Override
    public Person runTest(Person person) {
        List<Question> questions = reader.getQuestionList();
        studentTest(person, questions);
        return person;
    }

    /**
     * Печать результатов теста
     * @param person персона с результатами теста
     */
    public void printTestResult(Person person) {
        if (person != null) {
            int passCount = (int) person.getTestResultList().stream().filter(TestResult::isCorrect).count();
            int answersCount = person.getTestResultList().size();
            int percent = (100 * passCount) / answersCount;
            System.out.println();
            System.out.println("----------------------");
            System.out.println(messageSource.getMessage("hello.user",
                    new String[]{person.getFirstName(), person.getLastName()}, localeService.getLocale()));
            if (percent >= prop.getPassPercent()) {
                System.out.println(messageSource.getMessage("test.done.message", null,
                        localeService.getLocale()));
            } else {
                System.out.println(messageSource.getMessage("test.false.message", null,
                        localeService.getLocale()));
            }
            System.out.println(messageSource.getMessage("test.stat.num",
                    new String[]{Integer.toString(passCount), Integer.toString(answersCount)},
                    localeService.getLocale()));
            System.out.println(messageSource.getMessage("test.stat.percent",
                    new String[]{Integer.toString(percent), Integer.toString(prop.getPassPercent())},
                    localeService.getLocale()));
        }
    }
}
