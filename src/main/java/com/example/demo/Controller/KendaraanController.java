package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.Model.Kendaraan;
import com.example.demo.Repository.KendaraanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("kendaraan")
public class KendaraanController {
    @Autowired
    KendaraanRepository kendaraanRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<Kendaraan> kendaraanList = kendaraanRepository.findAll();
        mView.addObject("kendaraanList", kendaraanList);
        mView.setViewName("pages/kendaraan/index");
        return mView;
    }

    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new Kendaraan());
        }
        return "pages/kendaraan/add";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Kendaraan kendaraan,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        kendaraanRepository.save(kendaraan);
        mView.setViewName("redirect:/kendaraan");
        return mView;
    }

}