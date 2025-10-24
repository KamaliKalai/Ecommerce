package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserService userService;
    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "admin_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && "ADMIN".equals(existingUser.getRole())
                && existingUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", existingUser);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Invalid admin credentials");
        return "admin_login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/admin/login";
        }
        model.addAttribute("message", "Welcome, " + user.getUsername());
        return "admin_dashboard";
    }

    @GetMapping("/add")
    public String addProductPage(Model model, HttpSession session) {
        checkAdmin(session);
        model.addAttribute("product", new Product());
        return "admin_add_product";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product, HttpSession session) {
        checkAdmin(session);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model, HttpSession session) {
        checkAdmin(session);
        model.addAttribute("product", productService.getProductById(id));
        return "admin_add_product";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product, HttpSession session) {
        checkAdmin(session);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        checkAdmin(session);
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String viewProducts(Model model, HttpSession session) {
        checkAdmin(session);
        model.addAttribute("products", productService.getAllProducts());
        return "admin_view_products";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model, HttpSession session) {
        checkAdmin(session);
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin_view_orders";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private void checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Unauthorized");
        }
    }
}
