import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class driver {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("records.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray recordsList = (JSONArray) obj;

            new MainFrame(recordsList);
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}