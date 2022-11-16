package tic.tac.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tic.tac.toe.repository.PlayerRepository;
import tic.tac.toe.repository.UserRepository;
import tic.tac.toe.entity.Player;
import tic.tac.toe.entity.User;

import java.util.List;

@Component
public class AdminService {

    private final UserRepository userDAO;
    private final PlayerRepository playerDAO;

    @Autowired
    public AdminService(UserRepository userDAO, PlayerRepository playerDAO) {
        this.userDAO = userDAO;
        this.playerDAO = playerDAO;
    }

    public List<User> select(){
        return userDAO.select();
    }


    public Player selectByUser(long id) {
        return playerDAO.select(id);
    }

    public void delete(long idUser){
        playerDAO.delete(selectByUser(idUser).getId());
        userDAO.delete(idUser);
    }
}
