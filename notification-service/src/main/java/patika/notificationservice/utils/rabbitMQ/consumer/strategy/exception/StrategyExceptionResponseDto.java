package patika.notificationservice.utils.rabbitMQ.consumer.strategy.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyExceptionResponseDto {
    private String message;
}