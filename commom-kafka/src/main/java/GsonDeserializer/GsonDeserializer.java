package GsonDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserializer implements Deserializer {
    private final Gson gson = new GsonBuilder().create();
    public static final String TYPE_CONFIG = "";
    private Class<? extends Object> type;

    @Override
    public void configure(Map configs, boolean isKey) {
        String typeName = String.valueOf(configs.get(TYPE_CONFIG));
        try{
            this.type = (Class) Class.forName(typeName);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Type for deserialization does not exists");
        }
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), type);
    }
}
