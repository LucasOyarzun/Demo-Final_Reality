package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code Axe} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class AxeTest extends AbstractWeaponTest {

    private static final String AXE_NAME = "Test Axe";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Axe testAxe;

    /**
     * Creates a new Axe
     */
    @BeforeEach
    void setUp() {
        testAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Axe(AXE_NAME,15,10),
                testAxe,
                new Axe("Different name",15,10),
                new Staff(AXE_NAME,15,10, 10));
    }
}