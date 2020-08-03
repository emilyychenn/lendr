package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataAccessor {
    ObjectMapper objectMapper = new ObjectMapper();

    // TODO: should there be anything in here?
    // EFFECTS: constructs data accessor
    public DataAccessor() {

    }

    // EFFECTS: saves account information to JSON file
    public boolean saveToFile(Account account) {
        try {
            System.out.println("enter save to file");
            FileOutputStream filePath = new FileOutputStream("./data/usrAccountFile.json");
            System.out.println("about to save");
            objectMapper.writeValue(filePath, account);
            System.out.println("Saved to: " + filePath.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // TODO: should this also do something?
        }
    }



}
