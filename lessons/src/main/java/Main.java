import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Посчитать сколько раз встречается каждое слово.*/
        String[] list = {"mutation",
                "warn",
                "identification",
                "miscarriage",
                "mutation",
                "supply",
                "goalkeeper",
                "location",
                "expect",
                "pure",
                "reaction",
                "aloof",
                "inch",
                "pony",
                "pull",
                "expect",
                "location",
                "review",
                "disagreement",
                "budget"};

        Set<String> uniqueList = new HashSet<String>(Arrays.asList(list));
        System.out.println(uniqueList);

        Map<String, Integer> wordCounter = new HashMap();
        convertToMap(wordCounter, uniqueList);

        for (String item :
                list) {
            if (wordCounter.containsKey(item)) {
                wordCounter.put(item, wordCounter.get(item) + 1);
            }
        }

        System.out.println(wordCounter);

        /*2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий
        и телефонных номеров. В этот телефонный справочник с помощью метода add()
        можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
        Следует учесть, что под одной фамилией может быть несколько телефонов
        (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.*/
        Phonebook phonebook = new Phonebook();
        phonebook.add("Eva", "+7 (906) 456-78-90");
        phonebook.add("Eva", "+7 (906) 123-45-67");
        phonebook.add("Bill", "+7 (906) 234-56-78");
        phonebook.add("Elizabeth", "+7 (915) 456-78-90");
        phonebook.add("Bill", "+7 (915) 987-65-43");
    }

    private static void convertToMap(Map<String, Integer> map, Set<String> set) {
        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            map.put(iter.next(), 0);
        }
    }
}
