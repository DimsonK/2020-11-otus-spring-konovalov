package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.exceptions.BadRequestException;
import ru.otus.spring.homework.springproject.mappers.IssueMapper;
import ru.otus.spring.homework.springproject.models.dto.IssueDto;
import ru.otus.spring.homework.springproject.models.entity.Issue;
import ru.otus.spring.homework.springproject.models.entity.IssueInstance;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;
import ru.otus.spring.homework.springproject.models.enums.OrderStatus;
import ru.otus.spring.homework.springproject.repositories.IssueInstanceRepository;
import ru.otus.spring.homework.springproject.repositories.IssueRepository;
import ru.otus.spring.homework.springproject.repositories.OrderRepository;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final InstanceService instanceService;
    private final IssueInstanceRepository issueInstanceRepository;

    public IssueServiceImpl(
        IssueRepository issueRepository,
        IssueMapper issueMapper,
        OrderRepository orderRepository,
        UserRepository userRepository,
        InstanceService instanceService,
        IssueInstanceRepository issueInstanceRepository) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.instanceService = instanceService;
        this.issueInstanceRepository = issueInstanceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<IssueDto> getAll() {
        log.debug("getAllIssue");
        return issueMapper.toDtoList(issueRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public IssueDto getIssue(Long issueId) {
        log.debug("getIssue");
        return issueMapper.toDto(issueRepository.getOne(issueId));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<IssueDto> getIssues(List<String> issueIds) {
        log.debug("getIssues");
        var ids = issueIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return issueMapper.toDtoList(issueRepository.findAllById(ids));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public IssueDto addIssue(IssueDto issueDto) {
        log.debug("addIssue");
        var entity = issueMapper.toEntity(issueDto, userRepository, orderRepository);
        return issueMapper.toDto(issueRepository.save(entity));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public IssueDto addIssueByOrderId(Long orderId) {
        // get order
        var order = orderRepository.findById(orderId).orElse(null);
        if (Objects.isNull(order)) {
            throw new BadRequestException("Заказ не найден");
        } else if (!order.getStatus().equals(OrderStatus.APPROVED)) {
            throw new BadRequestException("Статус заказа не подтвержден или заказ уже выдан");
        }
        // получить доступные к выдаче экземпляры книг
        var bookIds = order.getBooks().stream().map(book -> book.getId().toString()).collect(Collectors.toList());
        var availableInstances = instanceService.getAvailableInstanceList(bookIds);
        var instancesToIssue = bookIds.stream()
            .map(bookId -> {
                var instanceForBook = availableInstances.stream()
                    .filter(i -> i.getBook().getId().equals(Long.parseLong(bookId))).collect(Collectors.toList());
                if (!instanceForBook.isEmpty()) {
                    return instanceForBook.get(0);
                } else {
                    return null;
                }
            })
            .collect(Collectors.toList());
        if (instancesToIssue.isEmpty()) {
            throw new BadRequestException("Нет экземпляров к выдаче");
        }
        // Создать выдачу
        var issue = issueRepository.save(new Issue(null, LocalDateTime.now(), order.getUser(), order, List.of()));
        instancesToIssue.stream()
            .filter(Objects::nonNull)
            .forEach(instance -> issueInstanceRepository.save(new IssueInstance(issue, instance, IssueStatus.ISSUED, LocalDateTime.now())));
        order.setStatus(OrderStatus.GIVEN);
        orderRepository.save(order);
        return issueMapper.toDto(issue);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public IssueDto updateIssue(IssueDto issueDto) {
        log.debug("updateIssue");
        return issueMapper.toDto(issueRepository.save(issueMapper.toEntity(issueDto, userRepository, orderRepository)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteIssue(Long issueId) {
        log.debug("deleteIssue");
        issueRepository.deleteById(issueId);
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public long getCount() {
        return issueRepository.count();
    }
}
