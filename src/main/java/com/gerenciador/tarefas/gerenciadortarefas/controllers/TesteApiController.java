package com.gerenciador.tarefas.gerenciadortarefas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApiController {

    @GetMapping("/teste-api")
    private String testeApi() {
        return "API funcionando!";
    }

    @GetMapping("/teste-api-bem-vindo")
    public String testeBemVindo(@RequestParam(name = "nome")String nome) {
        return "Bem vindo, " + nome;
    }
}
