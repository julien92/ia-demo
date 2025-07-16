package com.example.orderapi;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  /**
   * Initialise une commande par défaut dans la base de données H2
   */
  @PostConstruct
  public void init() {
    Order defaultOrder = new Order(null, "user@example.com", "Commande de test");
    orderRepository.save(defaultOrder);
  }

  /**
   * Récupère une commande par défaut
   *
   * @return une commande exemple
   */
  public Order getOrder() {
    return orderRepository.findById(1L)
        .orElse(new Order(1L, "user@example.com", "Commande de test"));
  }

  /**
   * Récupère une commande par son ID
   *
   * @param id l'identifiant de la commande
   * @return la commande trouvée ou null si non trouvée
   */
  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  /**
   * Crée une nouvelle commande
   *
   * @param email       l'email de l'utilisateur
   * @param description la description de la commande
   * @return la commande créée
   */
  public Order createOrder(String email, String description) {
    Order order = new Order(null, email, description);
    return orderRepository.save(order);
  }
}