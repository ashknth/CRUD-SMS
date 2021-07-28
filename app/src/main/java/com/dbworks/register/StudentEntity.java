package com.dbworks.register;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity (tableName = "studentDB")
public class StudentEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="studentID")
    Integer id;

    @ColumnInfo(name="studentEmail")
    String studentEmail;

    @ColumnInfo(name="studentPhone")
        String studentPhone;

    @ColumnInfo (name="studentPassword")
    String studentPassword;

    @ColumnInfo (name="studentConfirmPassword")
    String studentConfirmPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentConfirmPassword() {
        return studentConfirmPassword;
    }

    public void setStudentConfirmPassword(String studentConfirmPassword) {
        this.studentConfirmPassword = studentConfirmPassword;
    }
}
