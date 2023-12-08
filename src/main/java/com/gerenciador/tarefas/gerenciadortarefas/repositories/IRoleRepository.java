package com.gerenciador.tarefas.gerenciadortarefas.repositories;

import com.gerenciador.tarefas.gerenciadortarefas.entities.Role;
import com.gerenciador.tarefas.gerenciadortarefas.permissoes.PermissaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByNome(PermissaoEnum nome);
}
