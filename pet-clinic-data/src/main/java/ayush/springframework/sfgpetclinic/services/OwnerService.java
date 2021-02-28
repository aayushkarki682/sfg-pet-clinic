package ayush.springframework.sfgpetclinic.services;

import ayush.springframework.sfgpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);



}
