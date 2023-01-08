package com.samhcoco.projects.spring.rabbitmq.banking.core;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class Auditable {
    private Date created;
    private Date modified;
}
