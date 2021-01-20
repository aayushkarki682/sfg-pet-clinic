package ayush.springframework.sfgpetclinic.bootstrap;

import ayush.springframework.sfgpetclinic.model.Owner;
import ayush.springframework.sfgpetclinic.model.Pet;
import ayush.springframework.sfgpetclinic.model.PetType;
import ayush.springframework.sfgpetclinic.model.Vet;
import ayush.springframework.sfgpetclinic.services.OwnerService;
import ayush.springframework.sfgpetclinic.services.PetTypeService;
import ayush.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);





        Owner owner1 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("212 S Cooper");
        owner1.setCity("Arlington");
        owner1.setTelephone("6825517764");

        Pet ayushPet = new Pet();
        ayushPet.setPetType(savedDogPetType);
        ayushPet.setOwner(owner1);
        ayushPet.setBirthDate(LocalDate.now());
        ayushPet.setName("Laxy");
        owner1.getPets().add(ayushPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Fiona");
        owner2.setLastName("Karki");
        owner2.setAddress("212 S Cooper");
        owner2.setCity("Arlington");
        owner2.setTelephone("6825517764");

        Pet fionasCat = new Pet();
        fionasCat.setName("Sabina");
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setOwner(owner2);
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();

        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets.....");

    }
}
