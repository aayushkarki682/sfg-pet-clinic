package ayush.springframework.sfgpetclinic.services.map;

import ayush.springframework.sfgpetclinic.model.Owner;
import ayush.springframework.sfgpetclinic.model.Pet;
import ayush.springframework.sfgpetclinic.services.OwnerService;
import ayush.springframework.sfgpetclinic.services.PetService;
import ayush.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);

    }

    @Override
    public Owner save(Owner object) {

        if(object != null){
            if(object.getPets() != null){
                object.getPets().forEach(pets ->{
                    if(pets.getPetType() != null){
                        if(pets.getPetType().getId() == null){
                            pets.setPetType(petTypeService.save(pets.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pets.getId() == null){
                        Pet savedPet = petService.save(pets);
                        pets.setId(savedPet.getId());
                    }
                });
            }


            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {

        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        //todo later
        return null;
    }
}
