
package com.roky.thunderspi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "commentprod")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCommentaire;

    Long idClient;

    String comment;

    Long likes;

    @JsonBackReference(value="prodcom")
    @ManyToOne
    Product procom;

    @CreationTimestamp
    Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    Date lastUpdated;


}

