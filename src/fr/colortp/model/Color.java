package fr.colortp.model;

import java.util.Locale;
import java.util.regex.Pattern;

public class Color {

    enum ColorIndex {
        RED,
        GREEN,
        BLUE,
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        if (isNotCorrectIntColorValue(red)) {
            throw new IllegalArgumentException("Le paramètre pour la couleur rouge n'est pas correct");
        }
        if (hexValue != null) {
            replaceSpecificHexColorValueWhenSettingIntColor(ColorIndex.RED, red);
        }
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        if (isNotCorrectIntColorValue(green)) {
            throw new IllegalArgumentException("Le paramètre pour la couleur verte n'est pas correct");
        }
        if (hexValue != null) {
            replaceSpecificHexColorValueWhenSettingIntColor(ColorIndex.GREEN, green);
        }
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        if (isNotCorrectIntColorValue(blue)) {
            throw new IllegalArgumentException("Le paramètre pour la couleur bleue n'est pas correct");
        }
        if (hexValue != null) {
            replaceSpecificHexColorValueWhenSettingIntColor(ColorIndex.BLUE, blue);
        }
        this.blue = blue;
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        if (isNotCorrectHexaDecimalString(hexValue) || hexValue == null) {
            throw new IllegalArgumentException("La chaîne de caractère n'est pas une couleur hexadécimale");
        }
        this.hexValue = hexValue;
        this.red = Integer.valueOf(hexValue.substring(1, 3), 16 );
        this.green = Integer.valueOf(hexValue.substring(3, 5), 16 );
        this.blue = Integer.valueOf(hexValue.substring(5, 7), 16 );
    }

    private int red;
    private int green;
    private int blue;
    private String hexValue;

    public Color(int pRed, int pGreen, int pBlue) {
        setRed(pRed);
        setGreen(pGreen);
        setBlue(pBlue);
        setHexValue(String.format("#%02x%02x%02x", pRed, pGreen, pBlue).toUpperCase(Locale.ROOT));
    }

    public Color (String hexValue) {
        setHexValue(hexValue);
        setRed(Integer.valueOf(hexValue.substring(1, 3), 16 ));
        setGreen(Integer.valueOf(hexValue.substring(3, 5), 16 ));
        setBlue(Integer.valueOf(hexValue.substring(5, 7), 16 ));
    }

    private boolean isNotCorrectIntColorValue(int pIntColorValue) {
        return pIntColorValue < 0 || pIntColorValue > 255;
    }

    private boolean isNotCorrectHexaDecimalString(String pHexValue) {
        return !Pattern.compile("#([0-9A-F]{3}|[0-9A-F]{6}|[0-9A-F]{8})").matcher(pHexValue).matches();
    }

    @Override
    public String toString() {
        return "[value="+ hexValue +", r=" + red + ", g=" + green + ", b=" + blue + "]";
    }

    private void replaceSpecificHexColorValueWhenSettingIntColor(ColorIndex color, int newColorIntValue) {
        String newStringPart = Integer.toHexString(newColorIntValue).toUpperCase(Locale.ROOT);
        switch (color) {
            case RED:
                hexValue = "#" + newStringPart + hexValue.substring(3, 7);
                break;
            case GREEN:
                hexValue = hexValue.substring(0, 3) +  newStringPart + hexValue.substring(5, 7);
                break;
            case BLUE:
                hexValue = hexValue.substring(0, 5) + newStringPart;
                break;
        }


    }
}
