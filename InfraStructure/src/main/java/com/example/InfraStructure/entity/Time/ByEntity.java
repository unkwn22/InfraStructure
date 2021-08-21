package com.example.InfraStructure.entity.Time;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass // ������� ��, �÷����� �ν��ϰ� �մϴ�.
@EntityListeners(AuditingEntityListener.class) // ����/���� �ð��� �ڵ����� �ݿ��ϵ��� ����
@Getter
public abstract class ByEntity extends TimeEntity{
	
	@CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
	
}
