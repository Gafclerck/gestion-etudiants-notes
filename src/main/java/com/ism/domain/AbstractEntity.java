package com.ism.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor 
public abstract class AbstractEntity {
    protected int id;
    protected LocalDateTime createAt;
    protected LocalDateTime updateAt;
}
