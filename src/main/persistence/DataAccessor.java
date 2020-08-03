package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataAccessor {
    ObjectMapper objectMapper = new ObjectMapper();

    // EFFECTS: constructs data accessor
    public DataAccessor() {
        // TODO: should there be anything in here?
    }

    // EFFECTS: saves account information to JSON file
    public boolean saveToFile(String fileName, Account account) {
        try {
            FileOutputStream filePath = new FileOutputStream(fileName);
            objectMapper.writeValue(filePath, account);
            System.out.println("Saved to: " + filePath.toString());
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public Account readFromFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filePath);
            Account account = objectMapper.readValue(file, Account.class);
            return account;
        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println(e.getMessage());
            return null;
        }
    }



}
