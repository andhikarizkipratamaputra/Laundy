package com.example.demo.Controller;

import com.example.demo.Model.Kendaraan;
import com.example.demo.Model.ProgrammerProfil;
import com.example.demo.Repository.KendaraanRepository;
import com.example.demo.Repository.ProgrammerProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("programmerprofil")
public class ProgrammerProfilController {
    @Autowired
    ProgrammerProfilRepository programmerProfilRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<ProgrammerProfil> programmerProfilList = programmerProfilRepository.findAll();
        mView.addObject("programmerprofilList", programmerProfilList);
        mView.setViewName("pages/programmerprofil/index");
        return mView;
    }

    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new ProgrammerProfil());
        }
        return "pages/programmerprofil/add";
    }


    @GetMapping("/cek")
    public ModelAndView cek(ModelAndView mView) {
        mView.setViewName("pages/programmerprofil/cek");
        return mView;
    }

    @GetMapping("/novi")
    public ModelAndView novi(ModelAndView mView) {
        mView.setViewName("pages/programmerprofil/novi");
        return mView;
    }
    @GetMapping("/ahkmad")
    public ModelAndView ahkmad(ModelAndView mView) {
        mView.setViewName("pages/programmerprofil/ahkmad");
        return mView;
    }

    @GetMapping("/raihan")
    public ModelAndView raihan (ModelAndView mView) {
        mView.setViewName("pages/programmerprofil/raihan");
        return mView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") ProgrammerProfil programmerProfil,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        programmerProfilRepository.save(programmerProfil);
        mView.setViewName("redirect:/programmerprofil");
        return mView;


    }
}