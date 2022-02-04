import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;
    // Добавляем константы, которые определяют размеры поля и экрана
    private final int COLS =9; // Столбцы
    private final int ROWS = 9; // Строки
    private final int BOMBS= 10; // Общее количество бомб
    private final int IMAGE_SIZE = 50; // Размер картинки

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }
    private void initPanel() {
        // Создаем анонимный класс, в котором каждый раз будет вызываться форма, которая будет отрисовывать все на экране
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE; // Получили координату X, куда мышка ткнула
                int y = e.getY() / IMAGE_SIZE; // Получили координату Y, куда мышка ткнула
                Coord coord = new Coord(x, y); // Получили переменную coord, куда мышка щелкнула
                if  (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord); // Нажата левая кнопка
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint(); // Обязательно перерисовываем форму, иначе изменения не будут видны
            }
        });
        // Создаем панель
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE)); // Задаем размер панели
        add(panel); // Добавляем панель на нашу форму
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "THINK TWICE!";
            case BOMBED: return "YOU LOSE! BIG BA-DA-BOOM!";
            case WINNER: return "CONGRATULATIONS";
            default: return "WELCOME!";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Программа закрывается, когда нажимаем на крестик в углу
        setTitle("Java Sweeper"); // Даем название файлу
        setResizable(false); // Не меняем размер окна
        setVisible(true); // Делаем, чтобы нашу форму было видно
        pack(); // Меняет размер формы
        setLocationRelativeTo(null); // Устанавливаем окно по центру
        setIconImage(getImage("icon")); // Добавляем иконку в нашу программу
    }

    // Устанавливаем всем картинки
    private void setImages() {
        for (Box box : Box.values()) // Тут картинки перебираем
            box.image = getImage(box.name()); // Для каждого экземпляра Box устанавливаем картинку при помощи функции getImage
    }

    // Создаем картинки на нашей панели
    private Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png"; // Создаем путь до нашей картинки
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // Создаем объект ImageIcon из нашего файла (строка 37)
        return icon.getImage(); // Возвращаем картинку
    }
}
