package com.roky.thunderspi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class FileDB implements Serializable {


	private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  
  private Long id;
  
  @NonNull
  private String name;
  
  @NonNull
  private String type;
  
  @Lob
  @NonNull
  private byte[] data;
  
  
  
  @OneToOne(mappedBy="files")
  @JsonBackReference 
  private Post post;
  



  
  
 
}