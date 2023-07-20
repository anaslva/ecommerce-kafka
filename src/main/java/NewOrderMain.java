import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        try(var dispatcher = new KafkaDispatcher()){

            var key = UUID.randomUUID().toString();
            var value = key + "1, 12467, 500";
            dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

            var email = "Thanks for ordering. Your order are being processed";
            dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
        }


    }


}
