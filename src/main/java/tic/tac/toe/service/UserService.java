package tic.tac.toe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tic.tac.toe.entity.Role;
import tic.tac.toe.repository.PlayerRepository;
import tic.tac.toe.repository.RoleRepository;
import tic.tac.toe.repository.UserRepository;
import tic.tac.toe.dto.UserDTO;
import tic.tac.toe.entity.Player;
import tic.tac.toe.entity.User;
import tic.tac.toe.dto.PlayerDTO;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            PlayerRepository playerRepository,
            RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
            AuthenticationManager authenticationManager,
            UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public User selectUser(UserDTO userDTO) {
        String login = userDTO.getLogin();

        User user = userRepository.select(login);
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, userDTO.getPassword(), userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            log.debug(String.format("Auto login %s successfully!", login));
        }
        return user;
    }

    public Player selectPlayer(long id) {
        return playerRepository.select(id);
    }

    public long insetPlayer(User user)  {
        try {
            userRepository.select(user.getLogin());
            throw new RuntimeException("Player with this login exist");
        } catch (Exception exception) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.select(2));
            user.setRoles(roles);
            long id = userRepository.insert(user);
            return playerRepository.insert(new Player(id, 0, 0));
        }
    }

    public void updatePlayer(PlayerDTO player)  {
        playerRepository.update(player);
    }
}
