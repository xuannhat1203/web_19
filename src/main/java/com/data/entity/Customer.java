package com.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Tạo một ứng dụng quản lý khách hàng sử dụng hibernate để tương tác vào db
//		+ id
//		+ first name
//		+ last name
//		+ phone
//		+ address
//		+ fileImage: Kiểu File upload lên
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String fileImage;
}
