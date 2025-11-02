import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    void newHorseWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
    }

    @Test
    void newHorseWithNullNameThrowsExceptionMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("emptyStringArgsProviderFactory")
    void newHorseWithEmptyNameThrowsException(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 0));
    }

    @ParameterizedTest
    @MethodSource("emptyStringArgsProviderFactory")
    void newHorseWithEmptyNameThrowsExceptionMessage(String argument) {
        Exception exception = assertThrows(Exception.class, () -> new Horse(argument, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void newHorseWithNegativeNumberSpeedThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Test", -1));
    }

    @Test
    void newHorseWithNegativeNumberSpeedThrowsExceptionMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("a", -1));
        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    void newHorseWithNegativeNumberDistanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Test", 0, -1));
    }

    @Test
    void newHorseWithNegativeNumberDistanceThrowsExceptionMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("a", 0, -1));
        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    void check_getNameReturnsFirstParameter() {
        assertEquals("Test", new Horse("Test", 0, 0).getName());
    }

    @Test
    void check_getSpeedReturnsSecondParameter() {
        assertEquals(10, new Horse("Test", 10, 0).getSpeed());
    }

    @Test
    void check_getDistanceReturnsThirdParameter() {
        assertEquals(20, new Horse("Test", 0, 20).getDistance());
    }

    @Test
    void check_getDistanceReturnsZeroIfConstructorWithTwoParameter() {
        assertEquals(0, new Horse("Test", 20).getDistance());
    }

    @Test
    void checkThat_getRandomDoubleCalledInside_moveMethodWithParameters () {
        try (MockedStatic<Horse> horseMockedStatic =  Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 0.0, 0.0);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 10.0, 0.2",
            "20.0, 10.0, 0.5",
            "15.0, 12.0, 0.4"
    })
    void checkThat_moveMethodAssignsDistanceValueCalculatedByFormula (double distance, double speed, double random) {
        try (MockedStatic<Horse> horseMockedStatic =  Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("Test", speed, distance);
            horse.move();
            assertEquals(distance + speed * random, horse.getDistance());
        }
    }


    static Stream<String> emptyStringArgsProviderFactory() {
        return Stream.of("", " ",  "  ", "\t", "\n", "\r");
    }

}
