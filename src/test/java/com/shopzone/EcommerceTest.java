package com.shopzone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EcommerceTest {

    @LocalServerPort
    private int port;

    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void createPage() {
        page = browser.newPage();
    }

    @AfterEach
    void closePage() {
        if (page != null) page.close();
    }

    @Test
    void testUserCanBuySpecificItem() {
        // 1. Navigate to the storefront
        page.navigate("http://localhost:" + port);
        
        // 2. Log in using the demo account
        page.getByText("Auto-Fill Partner Demo Account").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Access Marketplace")).click();

        // 3. Wait for the product grid to load
        page.waitForSelector("#product-grid .group");

        // 4. Find the specific product card for the Keychron Keyboard
        Locator productCard = page.locator("#product-grid .group")
            .filter(new Locator.FilterOptions().setHasText("Sony"));

        // 5. Click the "Add" button inside that specific card
        Locator addButton = productCard.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add"));
        addButton.click(); 
        
        // 6. Open the cart and submit the order
        page.locator("#cart-btn").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit Expense Order")).click();

        // 7. Wait for the success toast to appear and verify
        Locator successToast = page.locator("#toast-container div").filter(new Locator.FilterOptions().setHasText("placed"));
        successToast.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        
        assertTrue(successToast.isVisible(), "Success toast did not appear.");
    }
}