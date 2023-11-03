package com.maxmayev.autograph.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@ToString
@Table(name = "user_messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "auto_number")
    private String autoNumber;
    @Column(name = "message_text")
    private String messText;
    @Column(name = "good_or_bad")
    private int goodOrBad;
    @Column(name = "destination")
    private String destination;

}
