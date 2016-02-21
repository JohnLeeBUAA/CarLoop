package com.example.Lingyun.myapplication.backend;

/**
 * Created by Lingyun on 2/20/2016.
 */
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class TestEntity {
    @Id private String name;
    private String password; //MD5 encrypted
}
