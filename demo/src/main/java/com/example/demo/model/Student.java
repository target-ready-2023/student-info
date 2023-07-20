package com.example.demo.model;


import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private long id = 1234;
    private String firstName = "John";
    private String lastName = "Doe";
    private String emailId = "johndoe@gmail.com";
    private String bloodGroup = "O+";
    private String fatherName = "Bob";
    private String motherName = "Alice";
    private int age = 12;
    private String gender = "Male";
    private String address = "Bangalore";
    private int _class = 6;
    private String [] Subjects = new String [] {"Math","English"};
    private String extraCurricular = "Art";
    private String allergies = "None";
    private String transport = "Self";


}
