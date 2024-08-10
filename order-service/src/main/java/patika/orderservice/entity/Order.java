package patika.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patika.orderservice.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "order_code")
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ElementCollection
    @CollectionTable(
            name = "order_book_ids",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @Column(name = "book_id")
    private List<Integer> bookIds;

    @ElementCollection
    @CollectionTable(
            name = "order_magazine_ids",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @Column(name = "magazine_id")
    private List<Integer> magazineIds;
}

