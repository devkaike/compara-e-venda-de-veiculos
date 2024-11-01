package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.domain.Role;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.repository.RoleRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.repository.UsuarioRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.ServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ServiceResponseDTO listarUsuario(){
        return new ServiceResponseDTO(200, usuarioRepository.findAll());
    }

    public ServiceResponseDTO adicionarUsuario(Usuario usuario){
        Role basicRole = roleRepository.findByNome("USER");

        if(basicRole == null){
            new ServiceResponseDTO(404,"Role de usuário não encontrada.");
        }

        usuario.setRole(basicRole);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);

        return new ServiceResponseDTO(200, "cadastro realizado com sucesso!");
    }

    public ServiceResponseDTO buscarPorEmail(String email){
        Optional<Usuario> usuario =usuarioRepository.findByEmail(email);

        if(usuario.isEmpty()){
            return new ServiceResponseDTO(404, "Email não cadastrado.");
        }

        return new ServiceResponseDTO(200, usuario.get());
    }

}
