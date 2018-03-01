package com.example.leole.rectivity;

import org.junit.Test;
import Classes.CurrentCondition;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
    CurrentCondition nes = new CurrentCondition();
    nes.callAPI("");
    System.out.println();
    }
}