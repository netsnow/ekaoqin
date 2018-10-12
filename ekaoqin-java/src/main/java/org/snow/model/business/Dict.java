package org.snow.model.business;


import io.swagger.annotations.ApiModel;
import org.snow.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dict")
@ApiModel(value = "实体类-字典")
public class Dict extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length =30)
    @Size(min = 1, max = 30)
    @NotNull
    private String type;

    @Column(name = "dict_index", length =20, unique = true)
    @Size(min = 1, max = 20)
    @NotNull
    private String index;

    @Column(name = "text", length =50, unique = false)
    @Size(min = 1, max = 50)
    @NotNull
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
