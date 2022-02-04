package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coord size; // Координата размера нашего окна
    private static ArrayList<Coord> allCoords; // Создаем коллекцию из списка координат
    private static Random random = new Random(); // Генератор случайных чисел для создания бомб

    static void setSize(Coord _size) { // Функция для установки размера экрана
        size = _size;
        allCoords = new ArrayList<Coord>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coord(x, y));
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x &&
               coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x),
                         random.nextInt(size.y)); // Генерируем случайную координату
    }

    // Создаем метод, который расставляет единицы вокруг заданной клетки
    static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around; // Задаем переменную
        ArrayList<Coord> list = new ArrayList<Coord>(); // Создаем список всех координат, которые будут вокруг
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y))) // Проверяем, что такая переменная существует
                    if (!around.equals(coord)) // Проверяем, что наша координата не равна центральной клетке
                        list.add(around);
                    return list;
    }
}
