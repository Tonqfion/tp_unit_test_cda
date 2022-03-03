package fr.colortp.model;

import java.util.Locale;
import java.util.regex.Pattern;

public class Color {

    private static final int MAX_INT_VALUE = 255;
    private static final int MIN_INT_VALUE = 0;
    private static final String RED_STRING = "red";
    private static final String GREEN_STRING = "green";
    private static final String BLUE_STRING = "blue";
    private static final Pattern HEX_REGEX = Pattern.compile("#([0-9A-F]{3}|[0-9A-F]{6}|[0-9A-F]{8})");

    enum ColorIndex {
        RED,
        GREEN,
        BLUE,
    }

    private int red;
    private int green;
    private int blue;
    private char[] hexValue = new char[7];

    public Color(int pRed, int pGreen, int pBlue) {
        setRed(pRed);
        setGreen(pGreen);
        setBlue(pBlue);
        hexValue[0] = '#';
    }

    public Color(String hexValue) {
        setHexValue(hexValue);
    }

    private String errorMsgForSetter(String color) {
        return String.format("Le paramètre pour la couleur %s n'est pas correct", color);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        checkIntColorValue(red, RED_STRING);
        fillHexValueWithColorInt(ColorIndex.RED, red);
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        checkIntColorValue(green, GREEN_STRING);
        fillHexValueWithColorInt(ColorIndex.GREEN, green);
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        checkIntColorValue(blue, BLUE_STRING);
        fillHexValueWithColorInt(ColorIndex.BLUE, blue);
        this.blue = blue;
    }

    public String getHexValue() {
        return String.valueOf(hexValue);
    }

    public void setHexValue(String hexValue) {
        if (isNotCorrectHexaDecimalString(hexValue)) {
            throw new IllegalArgumentException("La chaîne de caractère n'est pas une couleur hexadécimale");
        }
        this.hexValue = hexValue.toCharArray();
        this.red = Integer.valueOf(hexValue.substring(1, 3), 16 );
        this.green = Integer.valueOf(hexValue.substring(3, 5), 16 );
        this.blue = Integer.valueOf(hexValue.substring(5, 7), 16 );
    }

    private void checkIntColorValue(int pIntColorValue, String colorString) {
        if (pIntColorValue < MIN_INT_VALUE || pIntColorValue > MAX_INT_VALUE) {
            throw new IllegalArgumentException(errorMsgForSetter(colorString));
        }
    }

    private boolean isNotCorrectHexaDecimalString(String pHexValue) {
        return pHexValue == null || !HEX_REGEX.matcher(pHexValue).matches();
    }

    @Override
    public String toString() {
        return "[value="+ getHexValue() +", r=" + red + ", g=" + green + ", b=" + blue + "]";
    }

    private void fillHexValueWithColorInt(ColorIndex colorType, int newColorIntValue) {
        String newStringPart = Integer.toHexString(newColorIntValue).toUpperCase(Locale.ROOT);

        if (newStringPart.length() == 1) {
            newStringPart = '0' + newStringPart;
        }

        switch (colorType) {
            case RED:
                hexValue[1] = newStringPart.charAt(0);
                hexValue[2] = newStringPart.charAt(1);
                break;
            case GREEN:
                hexValue[3] = newStringPart.charAt(0);
                hexValue[4] = newStringPart.charAt(1);
                break;
            case BLUE:
                hexValue[5] = newStringPart.charAt(0);
                hexValue[6] = newStringPart.charAt(1);
                break;
        }
    }
}
