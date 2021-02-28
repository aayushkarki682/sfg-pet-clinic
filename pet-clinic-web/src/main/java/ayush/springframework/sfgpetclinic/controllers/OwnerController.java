package ayush.springframework.sfgpetclinic.controllers;

import ayush.springframework.sfgpetclinic.model.Owner;
import ayush.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {

        this.ownerService = ownerService;
    }

//    @RequestMapping({"", "/", "/index", "/index.html"})
//    public String listOwners(Model model){
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findowners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){

        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        //find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if(results.isEmpty()){
            result.rejectValue("lastName", "not found", "not found");
            return "owners/findowners";
        } else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else{
            model.addAttribute("selections", results);
            return "owners/ownerlists";
        }

    }
}
