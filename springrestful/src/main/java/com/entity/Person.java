package com.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/2/13
 */
@Entity
@Table(name = "user")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_age")
    private String age;

    @Column(name = "user_sex")
    private String sex;

    @Column(name = "inputtime")
    private Date inputtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }
}
