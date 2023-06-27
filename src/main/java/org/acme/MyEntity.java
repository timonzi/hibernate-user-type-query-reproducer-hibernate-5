package org.acme;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class MyEntity {
    @Id
    @GeneratedValue
    public Long id;

    @Type(type = "org.acme.MyStringUserType")
    public MyStringWrapper field;


    public Long getId() {
        return id;
    }

    public MyStringWrapper getField() {
        return field;
    }

    public void setField(final MyStringWrapper field) {
        this.field = field;
    }
}
