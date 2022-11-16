package tic.tac.toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tic.tac.toe.entity.User;
import tic.tac.toe.dto.PlayerDTO;
import tic.tac.toe.model.PlayingField;
import tic.tac.toe.dto.UserDTO;
import tic.tac.toe.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final PlayerDTO player;
    private final PlayingField playingField;

    @Autowired
    public RegistrationController(UserService playerService, PlayerDTO player, PlayingField playingField) {
        this.userService = playerService;
        this.player = player;
        this.playingField = playingField;
    }

    @GetMapping("/")
    public String registration(@ModelAttribute("user") UserDTO userDTO) {
        return "register";
    }

    @PostMapping("/")
    public String registration(@ModelAttribute("user") UserDTO userDTO, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";

        long id = userService.insetPlayer(new User(userDTO.getLogin(), userDTO.getPassword()));
        player.setId(id);
        player.setSumGames(0);
        player.setSumWin(0);
        model.addAttribute("playingField", playingField);
        model.addAttribute("sumGame", 0);
        model.addAttribute("sumWin", 0);
        return "user/game";
    }
}
