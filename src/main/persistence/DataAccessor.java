package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Saves and loads user data.
 */

public class DataAccessor {
    ObjectMapper objectMapper = new ObjectMapper();

    // EFFECTS: constructs data accessor
    public DataAccessor() {
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

    // EFFECTS: loads information from file if it exists
    public Account readFromFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filePath);
            Account account = objectMapper.readValue(file, Account.class);
            return account;
        } catch (IOException e) {
            return null;
        }
    }

}
