package com.example.InfraStructure.entity.Time;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass // ������� ��, �÷����� �ν��ϰ� �մϴ�.
@EntityListeners(AuditingEntityListener.class) // ����/���� �ð��� �ڵ����� �ݿ��ϵ��� ����
@Getter
public abstract class TimeEntity {

    @CreatedDate 
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}