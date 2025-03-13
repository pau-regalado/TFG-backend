package model;

import es.ull.animal_shelter.backend.model.AdoptionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdoptionStatusTest {

    @Test
    void testEnumValues() {
        assertEquals(3, AdoptionStatus.values().length);
        assertEquals(AdoptionStatus.PENDING, AdoptionStatus.valueOf("PENDING"));
        assertEquals(AdoptionStatus.ACCEPTED, AdoptionStatus.valueOf("ACCEPTED"));
        assertEquals(AdoptionStatus.REJECTED, AdoptionStatus.valueOf("REJECTED"));
    }

    @Test
    void testEnumToString() {
        assertEquals("PENDING", AdoptionStatus.PENDING.toString());
        assertEquals("ACCEPTED", AdoptionStatus.ACCEPTED.toString());
        assertEquals("REJECTED", AdoptionStatus.REJECTED.toString());
    }

    @Test
    void testInvalidEnumValue() {
        assertThrows(IllegalArgumentException.class, () -> AdoptionStatus.valueOf("INVALID_STATUS"));
    }
}
