package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest extends AbstractWeaponTest {

    private static final String BOW_NAME = "Test Bow";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Bow testBow;

    @BeforeEach
    void setUp() {
        testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Bow(BOW_NAME, 15, 10),
                testBow,
                new Bow("Different name", 15, 10),
                new Staff(BOW_NAME, 15, 10, 10));
    }
}
