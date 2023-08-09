package com.student.info.model;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")

public class Student {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "first_name", nullable = false, length = 100)
    @NotNull
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "email", nullable = false, length = 100)
    private String emailId;
    @Column(name = "blood_group", nullable = false, length = 3)
    private String bloodGroup;
    @Column(name = "father_name", nullable = false, length = 100)
    private String fatherName;
    @Column(name = "mother_name", nullable = false, length = 100)
    private String motherName;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "gender", nullable = false, length = 50)
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "class", nullable = false, length = 10)
    private int standard;
    @Column(name = "extracurricular")
    private String extraCurricular;
    @Column(name = "allergies")
    private String allergies;
    @Column(name = "transport", length = 100)
    private String transport;
    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

}
