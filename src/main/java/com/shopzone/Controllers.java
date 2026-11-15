package com.shopzone;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controllers {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    public Controllers(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // POST /api/register - Returns 409 Conflict if username exists
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Dtos.RegisterRequest request) {
        User user = userService.register(request.username(), request.password());
        return ResponseEntity.ok(Map.of("username", user.getUsername()));
    }

    // POST /api/login - Returns 401 Unauthorized if credentials are wrong
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Dtos.LoginRequest request) {
        User user = userService.login(request.username(), request.password());
        return ResponseEntity.ok(Map.of("username", user.getUsername()));
    }

    // GET /api/products - Returns a JSON array of all available store items
        @GetMapping("/products")
    public List<Product> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        return productService.getProducts(category, search);
    }

    // POST /api/orders - Creates a new order and returns the generated Order ID
    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody Dtos.OrderRequest request) {
        CustomerOrder order = orderService.placeOrder(request.username(), request.totalAmount(), request.itemCount());
        return ResponseEntity.ok(Map.of(
            "orderId", order.getId(),
            "status", order.getStatus().name()
        ));
    }
}