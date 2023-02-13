package com.example.libraryeventsconsumer.scheduler;

import com.example.libraryeventsconsumer.config.LibraryEventsConsumerConfig;
import com.example.libraryeventsconsumer.entity.FailureRecord;
import com.example.libraryeventsconsumer.jpa.FailureRecordRepository;
import com.example.libraryeventsconsumer.service.LibraryEventsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RetryScheduler {

    private FailureRecordRepository failureRecordRepository;
    private LibraryEventsService libraryEventsService;

    public RetryScheduler(FailureRecordRepository failureRecordRepository, LibraryEventsService libraryEventsService) {
        this.failureRecordRepository = failureRecordRepository;
        this.libraryEventsService = libraryEventsService;
    }

    @Scheduled(fixedRate = 10000)
    public void retryFailedRecords() {
        log.info("Retrying failed records started");
        failureRecordRepository.findAllByStatus(LibraryEventsConsumerConfig.RETRY)
                .forEach(failureRecord -> {
                    ConsumerRecord<Integer, String> consumerRecord = buildConsumerRecord(failureRecord);
                    try {
                        libraryEventsService.processLibraryEvent(consumerRecord);
                        failureRecord.setStatus(LibraryEventsConsumerConfig.SUCCESS);
                    } catch (Exception e) {
                        log.error("Exception in retryFailedRecords: {}", e.getMessage(), e);
                    }
                });
    }

    private ConsumerRecord<Integer, String> buildConsumerRecord(FailureRecord failureRecord) {

        return new ConsumerRecord<>(
                failureRecord.getTopic(),
                failureRecord.getPartition(),
                failureRecord.getOffset_value(),
                failureRecord.getKey(),
                failureRecord.getErrorRecord()
        );
    }
}
