package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;


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
    private long id = 1234;

    @Column(name = "first_name")
    private String firstName = "John";
    @Column(name = "last_name")
    private String lastName = "Doe";
    @Column(name = "email")
    private String emailId = "johndoe@gmail.com";
    @Column(name = "blood_group")
    private String bloodGroup = "O+";
    @Column(name = "father_name")
    private String fatherName = "Bob";
    @Column(name = "mother_name")
    private String motherName = "Alice";
    @Column(name = "age")
    private int age = 12;
    @Column(name = "gender")
    private String gender = "Male";
    @Column(name = "address")
    private String address = "Bangalore";
    @Column(name = "class")
    private int _class = 6;
//    @Column(name = "subject")
//    private String [] Subjects = new String [] {"Math","English"};
    @Column(name = "extracurricular")
    private String extraCurricular = "Art";
    @Column(name = "allergies")
    private String allergies = "None";
    @Column(name = "transport")
    private String transport = "Self";

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public int get_class() {
        return _class;
    }

//    public String[] getSubjects() {
//        return Subjects;
//    }

    public String getExtraCurricular() {
        return extraCurricular;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getTransport() {
        return transport;
    }

}
