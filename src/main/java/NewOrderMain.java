import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        try(var orderDispatcher = new KafkaDispatcher<Order>()){
            try(var emailDispatcher = new KafkaDispatcher<Email>()){
                for(int i=0; i<= 10; i++){
                    var orderId = UUID.randomUUID().toString();
                    var amount = Math.random() * 5000 + 1;
                    var userId = String.valueOf(i+1);
                    var order = new Order(userId, orderId, new BigDecimal(amount));
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    var email = new Email("new order", "Thanks for ordering. Your order are being processed");
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
                }
            }

        }

    }


}
