package tic.tac.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tic.tac.toe.dto.PlayerDTO;
import tic.tac.toe.entity.Player;
import tic.tac.toe.entity.User;
import tic.tac.toe.model.PlayingField;
import tic.tac.toe.dto.UserDTO;
import tic.tac.toe.service.AdminService;
import tic.tac.toe.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final AdminService adminService;
    private final PlayingField playingField;
    private final PlayerDTO playerDTO;

    @Autowired
    public LoginController(UserService playerService, AdminService adminService, PlayingField playingField, PlayerDTO playerDTO) {
        this.userService = playerService;
        this.adminService = adminService;
        this.playingField = playingField;
        this.playerDTO = playerDTO;
    }

    @GetMapping("/")
    public String authorization(@ModelAttribute("user") UserDTO userDTO) {
        return "login";
    }

    @PostMapping("/")
    public String authorization(@ModelAttribute("user") UserDTO userDTO, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "login";

        User user = userService.selectUser(userDTO);
        if(user.getRoles().stream().filter(it -> it.getName().equals("ADMIN")).toArray().length > 0){
            List<User> users = adminService.select();
            model.addAttribute("users", users);
            return "admin/select";
        } else {
            Player playerDB = userService.selectPlayer(user.getId());
            int sumGames = playerDB.getSumGames();
            int sumWin = playerDB.getSumWin();

            playerDTO.setId(playerDB.getId());
            playerDTO.setSumGames(sumGames);
            playerDTO.setSumWin(sumGames);

            model.addAttribute("playingField", playingField);
            model.addAttribute("sumGame", sumGames);
            model.addAttribute("sumWin", sumWin);
            return "user/game";
        }
    }
}
