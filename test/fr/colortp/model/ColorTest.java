package fr.colortp.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    private Color rgbColorTest;
    private Color hexColorTest;

    @BeforeEach
    public void setUp() {
        rgbColorTest = new Color(124, 255, 32);
        hexColorTest = new Color("#AA15FF");
    }

    /* ----------------------------- TEST NOT NULL ----------------------------- */

    @Test
    public void should_generate_not_null_object() {
        assertAll(() -> {
            assertNotNull(rgbColorTest);
            assertNotNull(hexColorTest);
        });
    }

    /* ----------------------------- TEST RGB CONSTRUCTOR ----------------------------- */

    @Test
    public void should_generate_correct_rgb_values() {
        assertAll("Un setter est non conforme",
                () -> assertEquals(124, rgbColorTest.getRed(), "Coucou, ça c'est pas bon " + rgbColorTest.getRed()),
                () -> assertEquals(255, rgbColorTest.getGreen(), "Coucou, ça c'est pas bon " + rgbColorTest.getGreen()),
                () -> assertEquals(32, rgbColorTest.getBlue(), "Coucou, ça c'est pas bon " + rgbColorTest.getBlue())
        );
    }

    @Test
    public void should_generate_correct_hex_value_from_rgb() {
        assertEquals("#7CFF20", rgbColorTest.getHexValue());
    }

    @Test
    public void should_generate_exception_from_incorrect_red_parameter() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Color(287, 15, 255)
        );
        assertEquals("Le paramètre pour la couleur red n'est pas correct", thrown.getMessage());
    }

    @Test
    public void should_generate_exception_from_incorrect_green_parameter() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Color(120, -78, 255)
        );
        assertEquals("Le paramètre pour la couleur green n'est pas correct", thrown.getMessage());
    }

    @Test
    public void should_generate_exception_from_incorrect_blue_parameter() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Color(78, 1, 289)
        );
        assertEquals("Le paramètre pour la couleur blue n'est pas correct", thrown.getMessage());
    }

    /* ----------------------------- TEST HEX CONSTRUCTOR ----------------------------- */

    @Test
    public void should_generate_correct_hex_value() {
        assertEquals("#AA15FF", hexColorTest.getHexValue());
    }

    @Test
    public void should_generate_correct_rgb_values_from_hex() {

        assertAll("Un setter est non conforme",
                () -> assertEquals(170, hexColorTest.getRed()),
                () -> assertEquals(21, hexColorTest.getGreen()),
                () -> assertEquals(255, hexColorTest.getBlue())
        );
    }

    @Test
    public void should_generate_exception_from_incorrect_hex_value() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Color("125454azeaze")
        );
        assertEquals("La chaîne de caractère n'est pas une couleur hexadécimale", thrown.getMessage());
    }

    @Test
    public void should_generate_exception_from_null_hex_value() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Color(null)
        );
        assertEquals("La chaîne de caractère n'est pas une couleur hexadécimale", thrown.getMessage());
    }


    /* ----------------------------- TEST SETTERS ----------------------------- */

    @Test
    public void should_set_new_red_value_from_int() {
        rgbColorTest.setRed(150);
        assertAll("Le setter rouge est non conforme",
                () -> assertEquals("#96FF20", rgbColorTest.getHexValue(), "Hex value is incorrect after using setRed"),
                () -> assertEquals(150, rgbColorTest.getRed(), "Red value is incorrect after using setRed")
        );
    }

    @Test
    public void should_throw_illegal_argument_exception_when_set_red_int() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                rgbColorTest.setRed(-87)
        );
        assertEquals("Le paramètre pour la couleur red n'est pas correct", thrown.getMessage());
    }

    @Test
    public void should_set_new_green_value_from_int() {
        rgbColorTest.setGreen(85);
        assertAll(() -> {
            assertEquals(85, rgbColorTest.getGreen());
            assertEquals("#7C5520", rgbColorTest.getHexValue());
        });
    }

    @Test
    public void should_throw_illegal_argument_exception_when_set_green_int() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                rgbColorTest.setGreen(-87)
        );
        assertEquals("Le paramètre pour la couleur green n'est pas correct", thrown.getMessage());
    }

    @Test
    public void should_set_new_blue_value_from_int() {
        rgbColorTest.setBlue(25);
        assertEquals(25, rgbColorTest.getBlue());
        assertEquals("#7CFF19", rgbColorTest.getHexValue());
    }

    @Test
    public void should_throw_illegal_argument_exception_when_set_blue_int() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                rgbColorTest.setBlue(-87)
        );
        assertEquals("Le paramètre pour la couleur blue n'est pas correct", thrown.getMessage());
    }

    @Test
    public void should_set_new_hex_valuer() {
        hexColorTest.setHexValue("#DD00DD");
        assertEquals("#DD00DD", hexColorTest.getHexValue());
        assertEquals(221, hexColorTest.getRed());
        assertEquals(0, hexColorTest.getGreen());
        assertEquals(221, hexColorTest.getBlue());
    }



    /* ----------------------------- TEST TO STRING ----------------------------- */

    @Test
    public void should_generate_string_correctly_from_rgb_constructor() {
        assertEquals("[value=#7CFF20, r=124, g=255, b=32]", rgbColorTest.toString());
    }

    @Test
    public void should_generate_string_correctly_from_hex_constructor() {
        assertEquals("[value=#AA15FF, r=170, g=21, b=255]", hexColorTest.toString());
    }

    @Test
    public void should_generate_string_correctly_from_rgb_constructor_after_set_new_red_value() {
        rgbColorTest.setRed(150);
        assertEquals("[value=#96FF20, r=150, g=255, b=32]", rgbColorTest.toString());
    }

    @Test
    public void should_generate_string_correctly_from_rgb_constructor_after_set_new_green_value() {
        rgbColorTest.setGreen(85);
        assertEquals("[value=#7C5520, r=124, g=85, b=32]", rgbColorTest.toString());
    }

    @Test
    public void should_generate_string_correctly_from_rgb_constructor_after_set_new_blue_value() {
        rgbColorTest.setBlue(25);
        assertEquals("[value=#7CFF19, r=124, g=255, b=25]", rgbColorTest.toString());
    }

    @AfterEach
    public void tearDown() {
        rgbColorTest = null;
        hexColorTest = null;
    }
}
