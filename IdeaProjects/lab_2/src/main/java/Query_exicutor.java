import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Query_exicutor {
    public HashMap<String, ArrayList<Object>> getDataFromDB(Connection conn, String sql) throws SQLException, IOException {

        String[] column_names = new String[]{"region_name", "country_name", "companies_name", "class", "thermal_capacity", "enrichment", "load_factor", "commercial_operation"};
        HashMap<String, ArrayList<Object>> active_reactors = new HashMap<>();
        for (String column : column_names) {
            active_reactors.put(column, new ArrayList<Object>());
        }
        Statement stmt = conn.createStatement();
        ResultSet rs;


        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (rs.next()) {
            active_reactors.get(column_names[0]).add(rs.getString(column_names[0]));
            active_reactors.get(column_names[1]).add(rs.getString(column_names[1]));
            active_reactors.get(column_names[2]).add(rs.getString(column_names[2]));
            active_reactors.get(column_names[3]).add(rs.getString(column_names[3]));
            active_reactors.get(column_names[4]).add(rs.getInt(column_names[4]));
            active_reactors.get(column_names[5]).add(rs.getInt(column_names[5]));
            active_reactors.get(column_names[6]).add(rs.getInt(column_names[6]));
            active_reactors.get(column_names[7]).add(rs.getDate(column_names[7]));
        }

        addLoadFactor(active_reactors.get(column_names[6]));
        create_burnup_collumn(active_reactors);
        return active_reactors;
    }

    public void addLoadFactor(ArrayList<Object> factors) {
        for (int i = 0; i < factors.size(); i++) {
            Object b = factors.get(i);
            if (b instanceof Integer && ((Integer) b) == 0) {
                factors.set(i, 90);
            }
        }
    }


    public HashMap<String, Double> ArrayListToHashMap(ArrayList<Object> entities) {
        HashMap<String, Double> map = new HashMap<>();
        entities.forEach(b -> {
            if (!(map.containsKey((String) b))) {
                map.put((String) b, (double) 0);
            }
        });
        return map;
    }

    public HashMap<String,Double> Coumpute(HashMap<String, ArrayList<Object>> reactors,  String column_name) {
        HashMap<String, Double> entities = ArrayListToHashMap(reactors.get(column_name));

        for (int i = 0; i < reactors.get("class").size(); i++) {
            double val = (Float) reactors.get("burnup").get(i);
            entities.put((String) reactors.get(column_name).get(i), entities.get(reactors.get(column_name).get(i)) + val);
        }
        return  entities;
    }


    public HashMap<String, Float> reactor_types(ArrayList<Reactor> reactors) {
        HashMap<String, Float> reactor_info =new HashMap<>();
        reactors.forEach(b->{
            reactor_info.put(b.getType(),(b.getBurnup()));
        });
        return reactor_info;
    }


    public void create_burnup_collumn(HashMap<String, ArrayList<Object>> active_reactors) throws IOException {

        FileReader reader = new FileReader();
        ArrayList<Reactor>  reactors_type_data = reader.getFromJSON(".\\ReactorsJSON.json");
        HashMap<String,Float> reactors_burnup = reactor_types(reactors_type_data);
        create_burnup(active_reactors,reactors_burnup);
    }

    public void create_burnup(HashMap<String, ArrayList<Object>> active_reactors,HashMap<String, Float> reactor_info){
        ArrayList<Object> burnup = new ArrayList<>();
        ArrayList<Object> reactors = active_reactors.get("class");
        for (Object reactor : reactors) {
            String a = (String) reactor;

            int lastNonSpaceIndex = a.trim().lastIndexOf(""); // find index of last non-space character
            a = a.substring(0, lastNonSpaceIndex);
            if (a.equals("PWR-1300") | a.equals("PWR-1300") | a.equals("PWR-1300")) {
                a = "PWR";
                System.out.println("here");
            }
            if (a.equals("VVER-1000") | a.equals("VVER-1200") | a.equals("VVER-440")) {
                a = "VVER";
            }
            burnup.add(reactor_info.getOrDefault(a, 40.0f));
            active_reactors.put("burnup", burnup);

        }
    }
}
