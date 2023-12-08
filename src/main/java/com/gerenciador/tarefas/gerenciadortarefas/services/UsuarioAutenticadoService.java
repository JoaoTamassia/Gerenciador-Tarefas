package com.gerenciador.tarefas.gerenciadortarefas.services;

import com.gerenciador.tarefas.gerenciadortarefas.entities.Usuario;
import com.gerenciador.tarefas.gerenciadortarefas.repositories.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioAutenticadoService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public UserDetails loadUserByUsername(String username){
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + "não encontrado"));

        List<SimpleGrantedAuthority> roles = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome().toString()))
                .collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), roles);

    }

}
