package com.example.orderapi;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

  private final Map<Long, Order> orders = new ConcurrentHashMap<>();

  /**
   * Initialise une commande par défaut dans la map
   */
  public OrderService() {
    Order defaultOrder = new Order(1L, "user@example.com", "Commande de test");
    orders.put(defaultOrder.getId(), defaultOrder);
  }

  /**
   * Récupère une commande par défaut
   *
   * @return une commande exemple
   */
  public Order getOrder() {
    return orders.getOrDefault(1L, new Order(1L, "user@example.com", "Commande de test"));
  }

  /**
   * Récupère une commande par son ID
   *
   * @param id l'identifiant de la commande
   * @return la commande trouvée ou null si non trouvée
   */
  public Order getOrderById(Long id) {
    return orders.get(id);
  }

  /**
   * Crée une nouvelle commande
   *
   * @param email       l'email de l'utilisateur
   * @param description la description de la commande
   * @return la commande créée
   */
  public Order createOrder(String email, String description) {
    // Dans un cas réel, on sauvegarderait la commande dans une base de données
    // et on générerait un ID unique
    long id = System.currentTimeMillis();
    Order order = new Order(id, email, description);
    orders.put(id, order);
    return order;
  }
}