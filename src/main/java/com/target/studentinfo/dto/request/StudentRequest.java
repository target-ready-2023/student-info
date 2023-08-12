package com.target.studentinfo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentRequest implements Serializable {
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
    private boolean isActive = true;

}
