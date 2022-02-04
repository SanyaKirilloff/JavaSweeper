package sweeper;

class Flag {
    private Matrix flagMap; // Создаем матрицу наших флагов
    private int countOfCLosedBoxes; // Переменная, которая считает количество закрытых клеток

    // Функция для запуска
    void start() {
        flagMap = new Matrix(Box.CLOSED); // Инициализируем нашу матрицу на закрытые ячейки
        countOfCLosedBoxes = Ranges.getSize().x * Ranges.getSize().y; // Количество закрытых клетов равно площади нашего поля
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfCLosedBoxes--;
    }

    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED: setClosedToBox(coord);
            break;
            case CLOSED: setFlagedToBox(coord);
            break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }


    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    int getCountOfClosedBoxes() {
        return countOfCLosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToCloseBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
