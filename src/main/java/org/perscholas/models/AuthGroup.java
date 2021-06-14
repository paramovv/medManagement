//package org.perscholas.models;
//
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.validation.constraints.NotBlank;
//import java.io.Serializable;
//
//@Entity
//@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Component
//public class AuthGroup implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long aId;
//    @NonNull@NotBlank
//    String aUsername;
//    @NonNull@NotBlank
//    String aAuthGroup;
//
//
//
//}
