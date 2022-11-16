package ba.unsa.etf.rpr.t3.z3;

import ba.unsa.etf.rpr.t3.z1.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ImenikTest {

    private static Imenik imenik = new Imenik();

    @BeforeAll
    public static void setup(){
        imenik.dodaj("Eldar", new FiksniBroj(Grad.SARAJEVO, "225-883"));
        imenik.dodaj("Dino", new FiksniBroj(Grad.ZENICA, "225-884"));
        imenik.dodaj("Amir", new MobilniBroj(61, "225-885"));
        imenik.dodaj("Chris", new MedunarodniBroj("+44", "7768878794"));
    }

    @Test
    public void testMockExternal(){
        // mock setup stage
        Imenik i = Mockito.mock(Imenik.class);
        Mockito.when(i.dajBroj("Eldar")).thenReturn("Nema nista");

        // test stage
        String test = i.dajBroj("Eldar");
        assertEquals(test, "Nema nista");
    }

    @Test
    public void testMockInternal(){
        Map<String, TelefonskiBroj> mapa = Mockito.mock(Map.class);
        Mockito.when(mapa.get("Eldar")).thenReturn(new FiksniBroj(Grad.MOSTAR, "225-883"));
        imenik.setBrojevi(mapa);

        String br = imenik.dajBroj("Eldar");
        assertNotEquals(br, "033/225-883");
        assertEquals(br, "036/225-883");
    }

}
