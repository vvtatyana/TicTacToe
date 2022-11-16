package tic.tac.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tic.tac.toe.entity.User;
import tic.tac.toe.service.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String select(Model model){
        List<User> users = adminService.select();
        model.addAttribute("users", users);
        return "admin/select";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, Model model){
        adminService.delete(id);
        List<User> users = adminService.select();
        model.addAttribute("users", users);
        return "admin/select";
    }
}
