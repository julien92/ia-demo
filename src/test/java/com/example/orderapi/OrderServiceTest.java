package com.example.orderapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  /**
   * This test class verifies the behavior of the getOrder method in the OrderService class. The
   * method should always return the default order with predefined values if no changes are made in
   * the service.
   */

  @Test
  public void testGetOrder_ReturnsDefaultOrder() {
    // Act
    Order order = orderService.getOrder();

    // Assert
    assertNotNull(order, "Order should not be null");
    assertEquals(1L, order.getId(), "Order ID should be 1");
    assertEquals("user@example.com", order.getEmail(), "Order email should match default");
    assertEquals("Commande de test", order.getDescription(),
        "Order description should match default");
  }
}