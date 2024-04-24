package env;

public class ApiKey {

    public String getKey() {
        return System.getenv("apiKey");
    }
}
