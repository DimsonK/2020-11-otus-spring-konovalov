package ru.otus.spring.homework.spring03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring03.config.AppProp;
import ru.otus.spring.homework.spring03.domain.Answer;
import ru.otus.spring.homework.spring03.domain.Person;
import ru.otus.spring.homework.spring03.domain.Question;
import ru.otus.spring.homework.spring03.domain.TestResult;

import java.util.List;
import java.util.Scanner;

@Service
public class StudentTestServiceImpl implements StudentTestService {
    private static final Logger log = LoggerFactory.getLogger(StudentTestServiceImpl.class);

    private final MessageSource messageSource;
    private final LocaleService localeService;
    private final CsvFileReader reader;
    private final AppProp prop;

    public StudentTestServiceImpl(
            MessageSource messageSource,
            LocaleService localeService,
            CsvFileReader reader,
            AppProp prop
    ) {
        this.messageSource = messageSource;
        this.localeService = localeService;
        this.reader = reader;
        this.prop = prop;
    }

    /**
     * Запускает тестирование студента
     * Результат теста сохраняется в экземпляре класса Person и отображается на экране
     */
    @Override
    public void runTest() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = reader.getQuestionList();

        System.out.println(messageSource.getMessage("welcome.to.test", null, localeService.getLocale()));
        System.out.print(messageSource.getMessage("enter.firstName", null, localeService.getLocale()));
        String personFirstName = scanner.next();
        System.out.print(messageSource.getMessage("enter.lastName", null, localeService.getLocale()));
        String personLastName = scanner.next();
        Person person = new Person(personFirstName, personLastName);
        studentTest(person, questions, scanner);
        printTestResult(person);
    }

    /**
     * Тестирование студента
     * @param person    студент
     * @param questions вопросы с вариантами ответов
     * @param scanner   ввод данных
     */
    private void studentTest(Person person, List<Question> questions, Scanner scanner) {
        questions.forEach(question -> {
            System.out.println(question.getId() + ". " + question.getName());
            List<Answer> answers = question.getAnswerList();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + answers.get(i).getName());
            }
            System.out.print(messageSource.getMessage("enter.answer", null, localeService.getLocale()));
            int answer = checkIntEnter(scanner, 0, answers.size());
            int answerId = answer - 1;
            person.addTestResult(new TestResult(
                    question.getId(),
                    answers.get(answerId).getId(),
                    answers.get(answerId).isCorrect()));
        });
    }

    /**
     * Проверка ввода
     * @param scanner ввод данных
     * @param min     минимальное допустимое значение
     * @param max     максимальное допустимое значение
     * @return введенное значение
     */
    private int checkIntEnter(Scanner scanner, int min, int max) {
        int answer;
        while (true) {
            try {
                answer = Integer.parseInt(scanner.next());
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
     * Печать результатов теста
     * @param person персона с результатами теста
     */
    private void printTestResult(Person person) {
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
