package com.target.studentinfo.model;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "first_name", length = 100)
    @NotNull
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "email", length = 100)
    private String emailId;
    @Column(name = "blood_group", length = 3)
    private String bloodGroup;
    @Column(name = "father_name", length = 100)
    private String fatherName;
    @Column(name = "mother_name", length = 100)
    private String motherName;
    @Column(name = "age")
    private int age;
    @Column(name = "gender",length = 50)
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "class", length = 10)
    private int standard;
    @Column(name = "extracurricular")
    private String extraCurricular;
    @Column(name = "allergies")
    private String allergies;
    @Column(name = "transport", length = 100)
    private String transport;
    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;

}
