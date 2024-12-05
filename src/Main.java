import java.util.ArrayList;
import java.util.List;
/**
 * Demonstrates the usage of the CustomSet class.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Creating empty set and add somethin
         */
        CustomSet<String> set1 = new CustomSet<>();
        set1.add("яблуко");
        set1.add("Банан");
        set1.add("Апельсин");

        System.out.println("Set 1: " + set1);
/**
 * Creating a set with a single element
 */
        CustomSet<String> set2 = new CustomSet<>("Вишня");
        System.out.println("Set 2: " + set2);
/**
 * Creating a set with using standart collection
 */
        List<String> list = new ArrayList<>();
        list.add("Аґрус");
        list.add("Диня");
        CustomSet<String> set3 = new CustomSet<>(list);

        System.out.println("Set 3: " + set3);
/**
 * Operation with sets
 */
        set1.addAll(set3);
        System.out.println("Set 1 після додавання Set 3: " + set1);

        set1.remove("яблуко");
        System.out.println("Set 1 після видаляння'яблуко': " + set1);

        set1.clear();
        System.out.println("Set 1 після відалення усього: " + set1);
    }
}

