package com.roky.thunderspi.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LibElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String fileType;
    String fileName;
    Long id_file ;
    Float rating;
    boolean isApproved;
    @ManyToOne(cascade = CascadeType.PERSIST)
    LibCategory libCategory;

    @ManyToOne
    @JoinColumn(name = "lib_element_id")
    private LibElement libElement;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<LibFile> libFiles = new LinkedHashSet<>();

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


    public Set<LibFile> getLibFiles() {return libFiles;}

    public void setLibFiles(Set<LibFile> libFiles) {this.libFiles = libFiles;}

    public void addLibFiles(LibFile l ) {libFiles.add(l);}

    public void removeLibFiles(LibFile l) {libFiles.remove(l);}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibElement that = (LibElement) o;
        return isApproved == that.isApproved && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(fileType, that.fileType) && Objects.equals(rating, that.rating) && Objects.equals(libCategory, that.libCategory) && Objects.equals(libElement, that.libElement) && Objects.equals(libFiles, that.libFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fileType, rating, isApproved, libCategory, libElement, libFiles);
    }
}
