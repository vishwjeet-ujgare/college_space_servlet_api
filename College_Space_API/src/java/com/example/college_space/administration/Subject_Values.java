/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.college_space.administration;

/**
 *
 * @author abc
 */


public class Subject_Values {

    private String sub_name;
        private int teacher_id;
    private String sub_abbr;

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSub_abbr() {
        return sub_abbr;
    }

    public void setSub_abbr(String sub_abbr) {
        this.sub_abbr = sub_abbr;
    }

    public Subject_Values(String sub_name, int teacher_id, String sub_abbr) {
        this.sub_name = sub_name;
        this.teacher_id = teacher_id;
        this.sub_abbr = sub_abbr;
    }



}
