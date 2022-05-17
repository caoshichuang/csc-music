package com.example.cscmusic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Role extends AbstractEntity {
    private String name;

    private String title;

}
