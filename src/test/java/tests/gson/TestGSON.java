package tests.gson;

import com.google.gson.Gson;
import test_data_models.LoginCred;
import tests.authen.LoginTestWithDataProvider;

public class TestGSON {
    public static void main(String[] args) {
        LoginCred loginCred =new LoginCred("teo@sth.com","12345678");
        Gson gson = new Gson();
        System.out.println("Object to json: " +gson.toJson(loginCred));

        String loginDataJson = "{\"email\":\"teo@sth.com\",\"password\":\"12345678\"}";
        LoginCred convertFromJson = gson.fromJson(loginDataJson, LoginCred.class);
        System.out.println("Json to Object: " +convertFromJson.toString());
    }
}
