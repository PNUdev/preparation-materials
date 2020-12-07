import java.util.List;

public class Main {

    private ItemDao itemDao = new ItemDao();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

        List<Item> items = itemDao.findAll();

        items.forEach(System.out::println);

    }

}
