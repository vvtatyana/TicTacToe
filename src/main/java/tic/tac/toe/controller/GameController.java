package tic.tac.toe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tic.tac.toe.dto.PlayerDTO;
import tic.tac.toe.game.Game;
import tic.tac.toe.service.UserService;

@Controller
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final UserService userService;
    private final Game game;
    private final PlayerDTO playerDTO;

    @Autowired
    public GameController(UserService playerService, Game game, PlayerDTO playerDTO) {
        this.userService = playerService;
        this.game = game;
        this.playerDTO = playerDTO;
        game.newGame();
    }

    @GetMapping("/{line}/{column}")
    public String nextMove(Model model, @PathVariable int line, @PathVariable int column) {
        game.setValue(line, column, 'x');
        if (game.isEnd() || game.isWin()) {
            String result;

            playerDTO.setSumGames(playerDTO.getSumGames() + 1);
            if (game.isEnd() && !game.isWin()) result = "Draw";
            else {
                if (game.isWin()) {
                    result = "You have won";
                    playerDTO.setSumWin(playerDTO.getSumWin() + 1);
                } else result = "You've lost";
            }
            model.addAttribute("result", result);

            userService.updatePlayer(playerDTO);

            return "user/end";
        }
        game.nextMove();
        model(model);
        return "user/game";
    }

    @GetMapping("/restart")
    public String restart(Model model) {
        game.newGame();
        model(model);
        return "user/game";
    }

    @GetMapping("/exit")
    public void exit() {
        System.exit(0);
    }

    public void model(Model model) {
        model.addAttribute("playingField", game.getPlayingField());
        model.addAttribute("sumGame", playerDTO.getSumGames());
        model.addAttribute("sumWin", playerDTO.getSumWin());
    }
}