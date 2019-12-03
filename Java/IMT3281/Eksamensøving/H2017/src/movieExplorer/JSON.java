package movieExplorer;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

    /**
     * Takes a resultset, and converts it to an instance of the JSON class
     * Source: https://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
     * @param rs The resultset to convert
     * @return The resulting JSON object
     * @throws SQLException
     * @throws JSONException
     */
    public static JSON convert(ResultSet rs) throws SQLException {
        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();

        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();

            for (int i=1; i<numColumns+1; i++) {
                String column_name = rsmd.getColumnName(i);

                if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
                    obj.put(column_name, rs.getArray(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
                    obj.put(column_name, rs.getInt(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
                    obj.put(column_name, rs.getBoolean(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
                    obj.put(column_name, rs.getBlob(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
                    obj.put(column_name, rs.getDouble(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
                    obj.put(column_name, rs.getFloat(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                    obj.put(column_name, rs.getInt(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
                    obj.put(column_name, rs.getNString(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                    obj.put(column_name, rs.getString(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
                    obj.put(column_name, rs.getInt(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
                    obj.put(column_name, rs.getInt(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                    obj.put(column_name, rs.getDate(column_name));
                }
                else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                    obj.put(column_name, rs.getTimestamp(column_name));
                }
                else{
                    obj.put(column_name, rs.getObject(column_name));
                }
            }

            json.add(obj);
        }

        return new JSON(json.toJSONString());
    }
}
