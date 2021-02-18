package ayush.springframework.sfgpetclinic.services.map;

import ayush.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long idVal = 1L;
    final String lastName = "Karki";


    @BeforeEach
    void setUp() {

        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(idVal).lastName(lastName).build());


    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(idVal);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(idVal));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 3L;
        Owner owner2 = Owner.builder().id(id).lastName("aayush").build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        System.out.println(savedOwner.getId());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void findById() {
    }

    @Test
    void findByLastName() {
        Owner karki = ownerServiceMap.findByLastName(lastName);
        assertNotNull(karki);
        assertEquals(lastName, karki.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner jpt = ownerServiceMap.findByLastName("jpt");
        assertNull(jpt);

    }
}