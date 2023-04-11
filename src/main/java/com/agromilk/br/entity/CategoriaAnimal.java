package com.agromilk.br.entity;

public enum CategoriaAnimal {
    BEZERRA("Bezerra"),
    BEZERRO("Bezerro"),
    NOVILHA("Novilha"),
    NOVILHO("Novilho"),
    TOURO("Touro"),
    VACA("Vaca");

    private final String nome;

    CategoriaAnimal(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public static CategoriaAnimal fromString(String value) {
        if (value != null) {
            for (CategoriaAnimal categoria : CategoriaAnimal.values()) {
                if (value.equalsIgnoreCase(categoria.name())) {
                    return categoria;
                }
            }
        }
        throw new IllegalArgumentException("Invalid categoria value: " + value);
    }

}