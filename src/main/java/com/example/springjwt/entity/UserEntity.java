package com.example.springjwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity //이 클래스가 스프링 부트한테 entity로 관리되기 위함
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY 설정을 해야 ID값이 겹치지 않고 자동으로 잘 생성이 됨
    private int id;

    private String username;
    private String password;
    private String role;
}
