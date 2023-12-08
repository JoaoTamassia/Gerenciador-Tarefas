package com.gerenciador.tarefas.gerenciadortarefas.services;

import com.gerenciador.tarefas.gerenciadortarefas.entities.Usuario;
import com.gerenciador.tarefas.gerenciadortarefas.repositories.IRoleRepository;
import com.gerenciador.tarefas.gerenciadortarefas.repositories.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setRoles(usuario.getRoles()
                .stream()
                .map(role -> roleRepository.findByNome(role.getNome()))
                .toList());

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.usuarioRepository.save(usuario);
    }
    public Usuario atualizarUsuario(Usuario usuario) {

        usuario.setRoles(usuario.getRoles()
                .stream()
                .map(role -> roleRepository.findByNome(role.getNome()))
                .toList());

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.usuarioRepository.save(usuario);
    }
    public void excluirUsuario(Usuario usuario) {

        this.usuarioRepository.deleteById(usuario.getId());
    }
    public List<Usuario> obtemUsuarios() {

        return this.usuarioRepository.findAll();
    }

}
