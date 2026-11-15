package com.shopzone;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Business Rule: Prevent duplicate usernames before saving to DB
    public User register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already taken: " + username);
        }
        return userRepository.save(new User(username, password));
    }

    // Optional: Validates user exists AND password matches; throws 401 if not.
    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }
}

@Service
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Handles the search and filter logic from the frontend
    public List<Product> getProducts(String category, String search) {
        List<Product> list = productRepository.findAll();

        // 1. Filter by Category
        if (category != null && !category.isBlank() && !category.equalsIgnoreCase("all")) {
            list = list.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
        }

        // 2. Filter by Search Query
        if (search != null && !search.isBlank()) {
            String q = search.toLowerCase();
            list = list.stream()
                .filter(p -> p.getName().toLowerCase().contains(q) || 
                             p.getDescription().toLowerCase().contains(q))
                .toList();
        }

        return list;
    }
}

@Service
class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Converts the checkout request into a CustomerOrder entity to be saved in H2
    public CustomerOrder placeOrder(String username, double totalAmount, int itemCount) {
        CustomerOrder order = new CustomerOrder(username, totalAmount, itemCount);
        return orderRepository.save(order);
    }
}