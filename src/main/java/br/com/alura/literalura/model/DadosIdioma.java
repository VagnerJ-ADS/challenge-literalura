package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosIdioma(@JsonAlias("languages") String idioma) {
}
