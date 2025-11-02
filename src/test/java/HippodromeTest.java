import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @Test
    void newHippodromeWithNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void newHippodromeWithNullThrowsExceptionMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    void newHippodromeWithEmptyListThrowsException() {
        List<Horse> horsesList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesList));
    }

    @Test
    void newHippodromeWithEmptyListThrowsExceptionMessage() {
        List<Horse> horsesList = new ArrayList<>();
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesList));
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @Test
    void check_getHorsesReturnsListContainsTheSameObjectsAndSameOrder() {
        List<Horse> horsesList = new ArrayList<>();
        for(int i =1 ; i <= 30; i++) {
            horsesList.add(new Horse("Horse" + i, i));
        }

        Iterable<Horse> actual = new Hippodrome(horsesList).getHorses();

        assertIterableEquals(horsesList, actual);
    }

    @Test
    void Check_moveMethodCalls_moveMethodOnAllHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockHorses.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for(Horse horse : mockHorses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void check_getWinnerMethodReturnsHorseWithLargestDistance() {
        List<Horse> horses = new ArrayList<>();
        for(int i = 1; i <= 50; i++) {
            Horse horse = new Horse("Horse" + i, 0, i);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(50, hippodrome.getWinner().getDistance());
    }
}
