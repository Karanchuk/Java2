import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {
    private Map<String, List<String>> map = new HashMap();

    public void add(String familyName, String phone) {
        if (map.containsKey(familyName)) {
            map.get(familyName).add(phone);
        } else {
            List<String> phoneList = new ArrayList();
            phoneList.add(phone);
            map.put(familyName, phoneList);
        }
    }

    public String get(String familyName) {
        String phones = "";
        if (map.containsKey(familyName)) {
            for (int i = 0; i < map.get(familyName).size(); i++) {
                phones += (phones.length() == 0 ? "" : ", ") + map.get(familyName).get(i);
            }
        }
        return phones;
    }
}
