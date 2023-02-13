package com.example.libraryeventsconsumer.jpa;

import com.example.libraryeventsconsumer.entity.FailureRecord;
import org.springframework.data.repository.CrudRepository;

public interface FailureRecordRepository extends CrudRepository<FailureRecord, Integer> {
}
