package org.snow.model.business;

import org.snow.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "room")
public class Room extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, unique = false)
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "limit_number", unique = false)
    private Long LimitNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLimitNumber() {
        return LimitNumber;
    }

    public void setLimitNumber(Long limitNumber) {
        LimitNumber = limitNumber;
    }
}
