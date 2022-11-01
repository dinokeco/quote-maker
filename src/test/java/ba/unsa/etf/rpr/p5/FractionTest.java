package ba.unsa.etf.rpr.p5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {

    @Test
    void defaultConstructor() {

        assertEquals(new Fraction().toString(), "1/1");
    }

    @Test
    void divisionByZeroConstructor() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Fraction(5, 0);
            }
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Fraction(5, 0);
        });
    }

    @Test
    void reduceable() {
        assertTrue(new Fraction(15, 6).isReducable());
    }

    @Test
    void notReducable() {
        assertFalse(new Fraction(1, 1).isReducable());
    }

    @Test
    void reduce() {
        final Fraction r = new Fraction(15, 6);
        r.reduce();
        assertAll(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(r.getNumerator(), 5);
                assertEquals(r.getDenominator(), 2);
            }
        });
    }

    @Test
    void toDouble() {
        Fraction r = new Fraction(15, 6);
        r.reduce();
        assertEquals(2.5, r.toDouble());
    }

    @Test
    void setAndReduce() {
        final Fraction r = new Fraction(3, 1);
        r.setNumerator(4);
        r.setDenominator(8);
        r.reduce();
        assertAll(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(r.getNumerator(), 1);
                assertEquals(r.getDenominator(), 2);
            }
        });
    }
}
