package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;

    @GetMapping("/dashboard")
    public String userDashboard(Model model, HttpSession session,
                                @RequestParam(value = "message", required = false) String message) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getUserOrders(user));
        model.addAttribute("message", message);
        return "user_dashboard";
    }

    @GetMapping("/confirm-buy/{productId}")
    public String confirmBuy(@PathVariable Long productId, @RequestParam int quantity,
                             HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Product product = productService.getProductById(productId);
        if (product == null || quantity <= 0 || quantity > product.getStock()) {
            return "redirect:/user/dashboard?message=Invalid+quantity";
        }

        double totalPrice = product.getPrice() * quantity;
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", totalPrice);

        return "confirm_buy";
    }

    @PostMapping("/buy")
    public String buyProduct(@RequestParam Long productId, @RequestParam int quantity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Product product = productService.getProductById(productId);
        if (product != null && product.getStock() >= quantity) {
            Order order = new Order();
            order.setUser(user);
            order.setProduct(product);
            order.setQuantity(quantity);
            orderService.placeOrder(order);

            product.setStock(product.getStock() - quantity);
            productService.addProduct(product);
            return "redirect:/user/dashboard?message=Purchase+successful!";
        }
        return "redirect:/user/dashboard?message=Insufficient+stock!";
    }

    @GetMapping("/orders")
    public String userOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("orders", orderService.getUserOrders(user));
        return "user_orders";
    }
}
