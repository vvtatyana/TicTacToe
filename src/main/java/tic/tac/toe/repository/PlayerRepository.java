package tic.tac.toe.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tic.tac.toe.entity.Player;
import tic.tac.toe.dto.PlayerDTO;

@Repository
public class PlayerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PlayerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Player select(long id) {
        Session session = sessionFactory.openSession();
        Player player = session.get(Player.class, id);
        session.close();
        return player;
    }

    @Transactional
    public long insert(Player player) {
        Session session = sessionFactory.openSession();
        long id = (long) session.save(player);
        session.close();
        return id;
    }

    @Transactional
    public void update(PlayerDTO newPlayer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Player player = session.load(Player.class, newPlayer.getId());
        player.setSumGames(newPlayer.getSumGames());
        player.setSumWin(newPlayer.getSumWin());
        session.update(player);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void delete (long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Player.class, id));
        session.getTransaction().commit();
        session.close();
    }
}
