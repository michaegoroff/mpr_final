package com.pjatk.MPRSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;

@Controller
public class ThymeleafController {


    private MyRestService service;

    @Autowired
    public ThymeleafController(MyRestService service){
        this.service = service;
    }

    @GetMapping(value = "/welcome")
    public String getWelcomeView(){
        return "welcome";
    }

    @GetMapping(value = "/index")
    public String getIndexView(Model model){
        model.addAttribute("cats",service.findAll());
        return "index";
    }

    @GetMapping(value = "/addCat")
    public String getAddView(Model model){
        model.addAttribute("cat",new Cat("",0,""));
        return "addCat";
    }

    @PostMapping(value = "/addCat")
    public String addCat(Cat cat, Model model, RedirectAttributes redirectAttributes){
        if(cat.getAge() < 0){
            model.addAttribute("errorMessage","Age cannot be less than 0");
            return "addCat";
        }
        service.addCat(cat);
        redirectAttributes.addFlashAttribute("successMessage","Cat created");
        return "redirect:/index";
    }

    @GetMapping(value = "/updateCat/{id}")
    public String getUpdateView(@PathVariable("id") Long id, Model model){
        model.addAttribute("cat",new Cat("",0,""));
        model.addAttribute("catId",id);
        return "updateCat";
    }

    @PostMapping(value = "/updateCat/{id}")
    public String updateCat(@PathVariable("id") Long id, @ModelAttribute Cat cat, Model model, RedirectAttributes redirectAttributes){
        if(cat.getAge() < 0){
            model.addAttribute("errorMessage","Age cannot be less than 0");
            return "updateCat";
        }
        service.updateCat(id, cat);
        redirectAttributes.addFlashAttribute("successMessage","Cat successfully updated");
        return "redirect:/index";
    }

    @GetMapping(value = "/deleteCat/{id}")
    public String getDeleteView(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        service.deleteCat(id);
        redirectAttributes.addFlashAttribute("successMessage","Cat successfully deleted");
        return "redirect:/index";

    }

}
