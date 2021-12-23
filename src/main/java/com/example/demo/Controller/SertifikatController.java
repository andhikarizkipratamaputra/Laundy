package com.example.demo.Controller;

import com.example.demo.Model.GedungParkir;
import com.example.demo.Model.Sertifikat;
import com.example.demo.Repository.GedungParkirRepository;
import com.example.demo.Repository.SertifikatRepository;
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
@RequestMapping("sertifikat")
public class SertifikatController {

    @Autowired
    SertifikatRepository sertifikatRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<Sertifikat>  sertifikatList= sertifikatRepository.findAll();
        mView.addObject("sertifikatList", sertifikatList);
        mView.setViewName("pages/sertifikat/index");
        return mView;
    }
    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new Sertifikat());
        }
        return "pages/sertifikat/add";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Sertifikat sertifikat,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        sertifikatRepository.save(sertifikat);
        mView.setViewName("redirect:/sertifikat");
        return mView;
    }
}