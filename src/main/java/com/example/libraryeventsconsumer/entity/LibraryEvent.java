package com.example.libraryeventsconsumer.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class LibraryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libraryEventId;

    @Enumerated(EnumType.STRING)
    private LibraryEventType libraryEventType;

    @OneToOne(mappedBy = "libraryEvent", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private Book book;
}
