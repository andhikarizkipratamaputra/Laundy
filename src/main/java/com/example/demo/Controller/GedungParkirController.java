package com.example.demo.Controller;

import com.example.demo.Model.GedungParkir;
import com.example.demo.Repository.GedungParkirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("gedungparkir")
public class GedungParkirController {

    @Autowired
    GedungParkirRepository gedungParkirRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<GedungParkir>  gedungParkirList= gedungParkirRepository.findAll();
        mView.addObject("gedungParkirList", gedungParkirList);
        mView.setViewName("pages/gedungparkir/index");
        return mView;
    }
    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new GedungParkir());
        }
        return "pages/gedungparkir/add";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") GedungParkir gedungParkir,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {

        String GedungParkir= "12345";
        if(gedungParkirRepository.findMaxId()!=null){
            Long generateId = gedungParkirRepository.findMaxId().getId()+1;
            GedungParkir= "12345"+generateId.toString();
        }
        gedungParkir.setNama(GedungParkir);
        gedungParkirRepository.save(gedungParkir);
        mView.setViewName("redirect:/gedungparkir");
        return mView;

    }

    @GetMapping("/delete/{id}")
    public String deleteGedungParkir(@PathVariable("id") Long id, Model model) {
        GedungParkir gedungParkir = gedungParkirRepository.findById(id).get();
        gedungParkirRepository.delete(gedungParkir);
        model.addAttribute("gedungparkir", gedungParkirRepository.findAll());
        return "redirect:/gedungparkir";
    }

}