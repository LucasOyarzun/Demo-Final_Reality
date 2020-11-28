package com.github.LucasOyarzun.finalreality.model.weapon.classes;

import com.github.LucasOyarzun.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code Staff} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Lucas Oyarzun Mendez.
 */
public class StaffTest extends AbstractWeaponTest {

    private static final String STAFF_NAME = "Test Staff";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;
    private static final int MAGIC_DAMAGE = 10;

    private Staff testStaff;

    /**
     * Creates a new Staff
     */
    @BeforeEach
    void setUp() {
        testStaff = new Staff(STAFF_NAME, DAMAGE,SPEED, MAGIC_DAMAGE);
    }

    /**
     * Checks that the class' constructor works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Staff(STAFF_NAME, 15, 10,10),
                testStaff,
                new Staff("Different name", 15, 10, 10),
                new Sword(STAFF_NAME, 15, 10));
    }
}