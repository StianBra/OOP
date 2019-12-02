package movieExplorer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class containing code for all JSON-operations
 */
public class JSON {

    private HashMap<String, Object> items = new HashMap<>();
    private ArrayList<Object> arrays = new ArrayList<>();

    /**
     * Constructor for the JSON class, takes a JSON-string as parameter
     * If the parameter is a JSONArray then they are added to the arraylist,
     * if it is a JSONObject then they are added to the hashmap.
     * @param json The JSON-encoded string to add
     */
    public JSON(String json) {
        JSONParser parser = new JSONParser();
        Object obj = null;


        // Tries to parse the string into a general object
        try {
            obj = parser.parse(json);
        } catch (ParseException e) {
            System.err.println("Encountered error during JSON parsing! " + e.getMessage());
        }

        assert obj != null;

        // Checks if the object is a JSON Array
        if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;

            // Iterates through every object in the JSON Array
            for (Object temp : jsonArray) {
                // If the object is another JSON Array, add it to the arraylist as another JSON Array (parsed 'recursively')
                if (temp instanceof JSONArray) {
                    arrays.add(new JSON(((JSONArray) temp).toJSONString()));

                // If the object is a JSON Object, add it to the arraylist (as a 'recursively' parsed JSON object)
                } else if (temp instanceof JSONObject) {
                    arrays.add(new JSON(((JSONObject) temp).toJSONString()));

                // The object is a single value, add it to the arraylist
                } else {
                    arrays.add(temp);
                }
            }

        // Checks if the object is a JSON Object
        } else if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;

            // Iterates through every key/value pair in the JSON Object
            for (String key : (Iterable<String>) jsonObject.keySet()) {
                Object temp = jsonObject.get(key);

                // If the sub-item is a JSON Array, add it to the hashmap (as a 'recursively' parsed JSON Array)
                if (temp instanceof JSONArray) {
                    items.put(key, new JSON(((JSONArray) temp).toJSONString()));

                // If the sub-item is a JSON Object, add it to the hashmap (as a 'recursively' parsed JSON Object)
                } else if (temp instanceof JSONObject) {
                    items.put(key, new JSON(((JSONObject) temp).toJSONString()));

                // If the sub-item is just a key/value pair, add it directly to the hashmap
                } else {
                    items.put(key, temp);
                }
            }

        // The object was neither a JSON Array or JSON Object, throw exception
        } else {
            throw new IllegalArgumentException("Parameter to JSON constructor must be either a JSONObject og JSONArray!");
        }
    }

    /**
     * Gets the value of a given key
     * @param key The key to find the corresponding value for
     * @return The value of the key in 'items'
     */
    public Object getValue(String key) {
        return items.get(key);
    }

    /**
     * Gets the JSON Object for a given key
     * @param key The key to find the corresponding JSON Object for
     * @return The JSON Object with the given key, from 'items'
     */
    public JSON get(String key) {
        return (JSON) items.get(key);
    }

    /**
     * Gets the JSON Object for a given index
     * @param index The index to find the corresponding JSON Object for
     * @return The JSON Object with the given index, from 'arrays'
     */
    public JSON get(int index) {
        return (JSON) arrays.get(index);
    }

    /**
     * Finds the size of 'this' JSON instance, either based on the items hashmap (if 'this' contains a JSONObject),
     * or the arraylist (if 'this' contains a JSONArray)
     * @return The size of 'this' primary variable
     */
    public int size() {
        if (items.size() > 0) {
            return items.size();
        } else {
            return arrays.size();
        }
    }
}
