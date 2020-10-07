class Task1 {
    public static Language create ( Matrix2D matrix2D ) {
        Language l = new DigitLanguage(matrix2D);
        return l;
    }
}
