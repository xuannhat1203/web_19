package com.data.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDto {
    private int id;
    @NotNull(message = "First name is required")
    @Size(min = 3, max = 10, message = "First name must be between 3 and 10 characters")
    private String firstName;
    @NotNull(message = "Last name is required")
    @Size(min = 3, max = 10, message = "Last name must be between 3 and 10 characters")
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private MultipartFile fileImage;

    public CustomerDto() {
    }

    public CustomerDto(int id, String firstName, String lastName,String email, String phone, String address, MultipartFile fileImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.fileImage = fileImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getFileImage() {
        return fileImage;
    }

    public void setFileImage(MultipartFile fileImage) {
        this.fileImage = fileImage;
    }
}
