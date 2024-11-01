package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.repository;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
