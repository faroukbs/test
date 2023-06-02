package com.roky.thunderspi.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LibCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLibCategory;
    String categoryName;

    @OneToMany(mappedBy = "libCategory",cascade = CascadeType.REMOVE)
    Set<LibElement> libElements;
}
