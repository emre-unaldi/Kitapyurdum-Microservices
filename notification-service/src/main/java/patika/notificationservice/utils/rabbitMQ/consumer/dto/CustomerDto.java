package patika.notificationservice.utils.rabbitMQ.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.AccountType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer credit;
    private String phoneNumber;
    private Boolean isActive;
    private AccountType accountType;
}
