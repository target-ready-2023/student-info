package com.student.info.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse implements Serializable {

    private String firstName;
    private String lastName;
    private String emailId;
    private String bloodGroup;
    private String fatherName;
    private String motherName;
    private int age;
    private String gender;
    private String address;
    private int standard;
    private String extraCurricular;
    private String allergies;
    private String transport;

    private String errorMessage;

    // constructor for successful response
    public StudentResponse(String firstName, String lastName, String emailId, String bloodGroup, String fatherName,
                           String motherName, int age, String gender, String address, int standard,
                           String extraCurricular, String allergies, String transport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.bloodGroup = bloodGroup;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.standard = standard;
        this.extraCurricular = extraCurricular;
        this.allergies = allergies;
        this.transport = transport;
    }

    // constructor for error response with error message
    public StudentResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
