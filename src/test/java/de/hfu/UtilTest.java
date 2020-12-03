package de.hfu;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilTest {

    @Test
    public void istErstesHalbJahr_returns_true() {
        assertTrue(Util.istErstesHalbjahr(1));
    }

    @Test
    public void istErstesHalbJahr_returns_false() {
        assertFalse(Util.istErstesHalbjahr(8));
    }
}
