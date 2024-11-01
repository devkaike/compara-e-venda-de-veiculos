package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String nome;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public enum Values {

        ADMIN(1L),
        USER(2L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}