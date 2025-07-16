package com.example.orderapi;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @Test
  void getOrderById_existingId_returnsOrder() throws Exception {
    Order order = new Order();
    order.setId(1L);
    order.setDescription("Test Order");
    order.setEmail("test@example.com");

    when(orderService.getOrderById(1L)).thenReturn(order);

    mockMvc.perform(get("/order/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(order.getId()))
        .andExpect(jsonPath("$.description").value(order.getDescription()))
        .andExpect(jsonPath("$.email").value(order.getEmail()));
  }

  @Test
  void getOrderById_nonExistingId_returnsNotFound() throws Exception {
    when(orderService.getOrderById(anyLong())).thenReturn(null);

    mockMvc.perform(get("/order/100")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void createOrder_validParameters_returnsCreatedOrder() throws Exception {
    Order order = new Order();
    order.setId(1L);
    order.setDescription("New Order");
    order.setEmail("new@example.com");

    when(orderService.createOrder("new@example.com", "New Order")).thenReturn(order);

    mockMvc.perform(post("/order")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("email", "new@example.com")
            .param("description", "New Order"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(order.getId()))
        .andExpect(jsonPath("$.description").value(order.getDescription()))
        .andExpect(jsonPath("$.email").value(order.getEmail()));
  }

  @Test
  void createOrder_missingParameters_returnsBadRequest() throws Exception {
    mockMvc.perform(post("/order")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("email", ""))
        .andExpect(status().isBadRequest());
  }
}