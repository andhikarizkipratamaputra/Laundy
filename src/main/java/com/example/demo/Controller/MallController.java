package com.example.demo.Controller;

import com.example.demo.Model.Mall;
import com.example.demo.Model.ProgrammerProfil;
import com.example.demo.Repository.MallRepository;
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
@RequestMapping("mall")
public class MallController {
    @Autowired
    MallRepository mallRepository;

    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<Mall> mallList = mallRepository.findAll();
        mView.addObject("mallList",mallList);
        mView.setViewName("pages/mall/index");
        return mView;
    }

    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new Mall());
        }
        return "pages/mall/add";
    }

        @GetMapping ("/baca")
        public ModelAndView baca (ModelAndView mView) {
        mView.setViewName("pages/mall/baca");
        return mView;
        }




    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Mall mall,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        mallRepository.save(mall);
        mView.setViewName("redirect:/mall");
        return mView;


    }
}