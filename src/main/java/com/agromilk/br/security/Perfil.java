package com.agromilk.br.security;


public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), FUNCIONARIO(1, "ROLE_FUNCIONARIO");

    private Integer cod;
    private String descricao;

    private Perfil(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido!");
    }
}
