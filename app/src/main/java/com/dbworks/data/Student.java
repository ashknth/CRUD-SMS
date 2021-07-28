package com.dbworks.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey
    public int roll_no;

    @ColumnInfo(name="student_name")
    public String student_name;

    @ColumnInfo(name="contact_no")
    public String contact_no;

    @ColumnInfo(name="gender")
    public String gender;

    public Student(int roll_no, String student_name, String contact_no, String gender) {
        this.roll_no = roll_no;
        this.student_name = student_name;
        this.contact_no = contact_no;
        this.gender = gender;
    }
}
