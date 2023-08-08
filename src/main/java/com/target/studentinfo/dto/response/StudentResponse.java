package com.target.studentinfo.dto.response;

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

}
