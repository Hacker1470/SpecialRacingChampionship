package data.special;

public class RandomGenerator {
    /**
     * Получить рандомное число на отрезке [min, max]
     *
     * @param min левая граница (полож число)
     * @param max правая граница (полож число)
     * @return
     */
    public static double getDouble(double min, double max) {
        return Math.random() * Math.abs(min - max) + Math.min(min, max);
    }

    /**
     * Получить рандомное число в диапазоне
     *
     * @param min левая граница (полож число)
     * @param max правая граница (полож число)
     * @return
     */
    public static int getInteger(int min, int max) {
        return (int) Math.round(Math.random() * Math.abs(min - max) + Math.min(min, max));
    }
}
