package com.example.libraryeventsconsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class FailureRecord {
    @Id
    @GeneratedValue
    private Integer bookId;
    private String topic;

    @Column(name="key_value")
    private Integer key;

    private String errorRecord;
    private Integer partition;
    private Long offset_value;
    private String exception;
    private String status;
}
