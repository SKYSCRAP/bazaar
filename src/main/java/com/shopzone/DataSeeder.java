package com.shopzone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    // Runs exactly once on startup to seed the H2 database with default data
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, ProductRepository productRepo) {
        return args -> {
            // Only insert if the admin user doesn't already exist to avoid duplicate errors on restart
            if (userRepo.findByUsername("admin").isEmpty()) {
                userRepo.save(new User("admin", "1234"));
            }
            
            // Only insert products if the table is empty
            if (productRepo.count() == 0) {
                productRepo.save(new Product("Sony WH-1000XM5 Wireless Noise Canceling Headphones", "Industry-leading noise cancellation with 30-hour battery life.", 348.00, "Audio", "https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?w=600&auto=format&fit=crop&q=80", 4.8, 3420, "Partner Pick"));
                
                productRepo.save(new Product("Apple Watch Series 9 [GPS 41mm] Smartwatch", "Always-On Retina display and meeting alerts.", 399.00, "Wearables", "https://images.unsplash.com/photo-1546868871-7041f2a55e12?w=600&auto=format&fit=crop&q=80", 4.9, 5120, "Billable Approved"));
                
                productRepo.save(new Product("Logitech MX Master 3S Wireless Performance Mouse", "8K DPI optical sensor and ultra-fast scrolling.", 99.99, "Electronics", "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=600&auto=format&fit=crop&q=80", 4.7, 8940, "Analyst Favorite"));
                
                productRepo.save(new Product("Keychron Q1 Pro QMK/VIA Wireless Mechanical Keyboard", "Full CNC machined aluminum body.", 199.00, "Electronics", "https://images.unsplash.com/photo-1595225476474-87563907a212?w=600&auto=format&fit=crop&q=80", 4.8, 1280, "Top Rated"));
                
                productRepo.save(new Product("Dell UltraSharp U2723QE 27-Inch 4K USB-C Hub Monitor", "IPS Black technology and single-cable charging.", 549.99, "Home & Office", "https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=600&auto=format&fit=crop&q=80", 4.6, 2150, "WBS Eligible"));
                
                productRepo.save(new Product("Bose SoundLink Flex Bluetooth Portable Speaker", "Waterproof & dustproof IP67 design.", 149.00, "Audio", "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=600&auto=format&fit=crop&q=80", 4.8, 14300, "Best Seller"));
                
                productRepo.save(new Product("Ergonomic Mesh Office Chair with Lumbar Support", "Breathable elastic mesh, 3D adjustable armrests.", 279.00, "Home & Office", "https://images.unsplash.com/photo-1505843490538-5133c6c7d0e1?w=600&auto=format&fit=crop&q=80", 4.5, 4390, "Stipend Approved"));
                
                productRepo.save(new Product("Fujifilm X-T30 II Mirrorless Digital Camera", "26.1MP X-Trans CMOS 4 sensor and 4K video.", 899.00, "Electronics", "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=600&auto=format&fit=crop&q=80", 4.9, 980, "Limited Stock"));
            }
        };
    }
}