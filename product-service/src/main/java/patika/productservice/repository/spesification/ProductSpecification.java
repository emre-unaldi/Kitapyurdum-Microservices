package patika.productservice.repository.spesification;

import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import patika.productservice.entity.Book;
import patika.productservice.entity.Magazine;
import patika.productservice.entity.dto.request.ProductSearchRequest;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSpecification {

    public static Specification<Book> initBookSpecification(ProductSearchRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (request.getName()!= null) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            if (request.getAmount()!= null) {
                predicateList.add(criteriaBuilder.greaterThan(root.get("amount"), request.getAmount()));
            }

            if (request.getSort()!= null &&!request.getSort().isEmpty()) {
                switch (request.getSort()) {
                    case "asc":
                        predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("amount"), request.getAmount()));
                        break;
                    case "desc":
                        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), request.getAmount()));
                        break;
                }
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    public static Specification<Magazine> initMagazineSpecification(ProductSearchRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (request.getName()!= null) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            if (request.getAmount()!= null) {
                predicateList.add(criteriaBuilder.greaterThan(root.get("amount"), request.getAmount()));
            }

            if (request.getSort()!= null &&!request.getSort().isEmpty()) {
                switch (request.getSort()) {
                    case "asc":
                        predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("amount"), request.getAmount()));
                        break;
                    case "desc":
                        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), request.getAmount()));
                        break;
                }
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
