package com.virtusa.polaris;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ApplicationTest {

    @Rule
    public ExpectedException expException = ExpectedException.none();

    @Test
    public void mainWithDefaultInputFile() {
        Application.main(new String[]{});
    }

    @Test
    public void mainWithRuntimeInputFile() {
        Application.main(new String[]{"input.txt"});
    }
}
