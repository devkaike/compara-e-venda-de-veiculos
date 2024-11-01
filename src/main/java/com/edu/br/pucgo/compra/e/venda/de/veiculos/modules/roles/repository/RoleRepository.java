package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.repository;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNome(String nome);
}
