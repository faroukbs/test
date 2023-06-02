package com.roky.thunderspi.entities;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProjectFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    @Lob
    private byte[] data;



    public ProjectFile(){}

    public ProjectFile(String name, String type, byte[] data)
    {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectFile that = (ProjectFile) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
