package com.agromilk.br.security;

import java.util.Set;
import java.util.stream.Collectors;

public class PerfilUtil {

    public static Set<Perfil> convertPerfis(Set<Integer> perfis) {
        return perfis.stream()
                .map(Perfil::toEnum)
                .collect(Collectors.toSet());
    }
}