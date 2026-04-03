package data.special;

public class RandomDoubleGenerator {
/**
     * Получить рандомное число в диапазоне
     * @param a левая граница (полож число)
     * @param b правая граница (полож число)
     * @return
     */
    public static double generate(double a, double b){
        return Math.random() * Math.abs(a - b) + Math.min(a, b);
    }
}
