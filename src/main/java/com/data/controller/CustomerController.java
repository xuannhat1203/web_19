package com.data.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.dto.CustomerDto;
import com.data.entity.Customer;
import com.data.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class CustomerController {
    private final Cloudinary cloudinary;
    public CustomerService customerService;
    public CustomerController(Cloudinary cloudinary, CustomerService customerService) {
        this.cloudinary = cloudinary;
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public String listCustomers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("customers", customerService.getCustomersByName(keyword));
        } else {
            model.addAttribute("customers", customerService.listCustomers());
        }
        return "customer-page";
    }
    @GetMapping("/customers/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "customer-add";
    }
    @PostMapping("/customers/add")
    public String addCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result, Model model) throws IOException {
       if (result.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "customer-add";
       }
        Customer customer = coverToCustomer(customerDto);
        Map<String,Object> uploadResult = cloudinary.uploader().upload(customerDto.getFileImage().getBytes(), ObjectUtils.emptyMap());
        customer.setFileImage(uploadResult.get("url").toString());
        if (customerService.addCustomer(customer)) {
            model.addAttribute("message", "Thêm khách hàng thành công");
        } else {
            model.addAttribute("message", "Thêm khách hàng thất bại");
            return "customer-add";
        }
        return "redirect:customer-page";
    }
    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.listCustomers().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (customer != null) {
            model.addAttribute("customer", coverToCustomerDto(customer));
            return "customer-edit";
        } else {
            model.addAttribute("message", "Khách hàng không tồn tại");
            return "redirect:customer-page";
        }
    }
    @PostMapping("customers/edit/{id}")
    public String editCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "customer-edit";
        }
        Customer customer = coverToCustomer(customerDto);
        customer.setId(customerDto.getId());
        Map<String,Object> uploadResult = cloudinary.uploader().upload(customerDto.getFileImage().getBytes(), ObjectUtils.emptyMap());
        customer.setFileImage(uploadResult.get("url").toString());
        if (customerService.updateCustomer(customer)) {
            model.addAttribute("message", "Cập nhật khách hàng thành công");
        } else {
            model.addAttribute("message", "Cập nhật khách hàng thất bại");
            return "customer-edit";
        }
        return "redirect:/customers";
    }
    @GetMapping("customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id, Model model) {
        if (customerService.deleteCustomer(id)) {
            model.addAttribute("message", "Xóa khách hàng thành công");
        } else {
            model.addAttribute("message", "Xóa khách hàng thất bại");
        }
        return "redirect:/customers";
    }
    public Customer coverToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        return customer;
    }
    public CustomerDto coverToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setAddress(customer.getAddress());
        return customerDto;
    }
}
