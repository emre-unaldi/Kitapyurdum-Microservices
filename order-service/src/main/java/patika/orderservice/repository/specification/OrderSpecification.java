package patika.orderservice.repository.specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.data.jpa.domain.Specification;
import patika.orderservice.entity.Order;
import patika.orderservice.entity.dto.request.OrderSearchRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderSpecification {
        public static Specification<Order> initOrderSpecification(OrderSearchRequest request) {
            return (root, query, criteriaBuilder) -> {

                List<Predicate> predicateList = new ArrayList<>();

                if (request.getOrderStatus() != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("orderStatus"), request.getOrderStatus()));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            };
        }

    }
