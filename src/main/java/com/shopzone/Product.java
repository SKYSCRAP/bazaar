package com.shopzone;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
    private double rating;
    private int reviewsCount;
    private String badge;

    public Product() {}
    
    public Product(String name, String description, double price, String category, String imageUrl, double rating, int reviewsCount, String badge) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
        this.badge = badge;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getImageUrl() { return imageUrl; }
    public double getRating() { return rating; }
    public int getReviewsCount() { return reviewsCount; }
    public String getBadge() { return badge; }
}