package org.vargattila;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Organization {

    @Id
    String id;

    String name;

    String contactName;

    String contactEmail;

    String contactPhone;
}
