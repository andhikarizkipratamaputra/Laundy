package com.example.demo.Controller;

import com.example.demo.Model.AreaParkir;
import com.example.demo.Model.GedungParkir;
import com.example.demo.Model.Kendaraan;
import com.example.demo.Repository.AreaParkirRepository;
import com.example.demo.Repository.GedungParkirRepository;
import com.example.demo.Repository.KendaraanRepository;
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
@RequestMapping("areaparkir")
public class AreaParkirController {

    @Autowired
    AreaParkirRepository areaParkirRepository;

    @Autowired
    GedungParkirRepository gedungParkirRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<AreaParkir>  areaParkirList= areaParkirRepository.findAll();
        mView.addObject("areaParkirList", areaParkirList);
        mView.setViewName("pages/areaparkir/index");
        return mView;
    }
    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new AreaParkir());
        }
        List<GedungParkir> gedungParkirList = gedungParkirRepository.findAll();
        model.addAttribute("gedungParkirList", gedungParkirList);
        return "pages/areaparkir/add";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") AreaParkir areaParkir,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        areaParkir.setAvailable(true);
        areaParkirRepository.save(areaParkir);
        mView.setViewName("redirect:/areaparkir");
        return mView;
    }
}