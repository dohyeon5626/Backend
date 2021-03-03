package com.jpaexample.jpa.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
}
