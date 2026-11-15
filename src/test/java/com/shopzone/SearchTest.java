package com.shopzone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class SearchTest {

    @LocalServerPort
    private int port;

    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
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
    void testSearchFilter() {
        // 1. Navigate and log in
        page.navigate("http://localhost:" + port);
        page.getByText("Auto-Fill Partner Demo Account").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Access Marketplace")).click();

        // 2. Wait for the full product grid to load (8 items)
        page.waitForSelector("#product-grid .group");
        assertEquals(8, page.locator("#product-grid .group").count(), "Should initially show all 8 products");

        // 3. Type "Sony" into the search bar
        page.locator("#search-input").fill("Sony");

        // 4. SMART WAIT: Wait up to 5 seconds for the frontend to fetch the filtered results and update the grid
        page.waitForCondition(() -> page.locator("#product-grid .group").count() == 1);

        // 5. Assert only 1 product is showing, and it is the correct one
        Locator products = page.locator("#product-grid .group");
        assertEquals(1, products.count(), "Should only show 1 product for the search 'Sony'");
        assertTrue(products.first().innerText().contains("Sony"), "The visible product should be the Sony headphones");
    }
}