package com.example.demo.Controller;

import com.example.demo.Model.KeluarParkir;
import com.example.demo.Model.ProgrammerProfil;
import com.example.demo.Repository.KeluarParkirRepository;
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
@RequestMapping("keluarparkir")
public class KeluarParkirController {
    @Autowired
    KeluarParkirRepository keluarParkirRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<KeluarParkir> keluarParkirList = keluarParkirRepository.findAll();
        mView.addObject("keluarparkirList", keluarParkirList);
        mView.setViewName("pages/keluarparkir/index");
        return mView;
    }

    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new KeluarParkir());
        }
        return "pages/keluarparkir/add";
    }


    @GetMapping("/keluarkendaraan")
    public ModelAndView cek(ModelAndView mView) {
        mView.setViewName("pages/keluarparkir/cek");
        return mView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") KeluarParkir keluarParkir,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        keluarParkirRepository.save(keluarParkir);
        mView.setViewName("redirect:/keluarparkir");
        return mView;


    }
}