package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code Knife} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class KnifeTest extends AbstractWeaponTest {

    private static final String KNIFE_NAME = "Test Knife";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Knife testKnife;

    /**
     * Creates a new Knife
     */
    @BeforeEach
    void setUp() {
        testKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Knife(KNIFE_NAME, 15, 10),
                testKnife,
                new Knife("Different name", 15, 10),
                new Staff(KNIFE_NAME, 15, 10, 10));
    }
}