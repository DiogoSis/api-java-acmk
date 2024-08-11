package com.acmk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Aluno {
    private UUID id;
    private String primeiroNome;
    private String sobrenome;
    private Date dataNascimento;
    private String genero;
    private String telefone;
    private String email;
    private String endereco;
    private Date dataEntrada;
    private String corFaixa;

//    //constructor sem ID
//    public Aluno(String primeiroNome, String sobrenome, Date dataNascimento, String genero,
//                 String telefone, String email, String endereco, Date dataEntrada, String corFaixa) {
//        this.primeiroNome = primeiroNome;
//        this.sobrenome = sobrenome;
//        this.dataNascimento = dataNascimento;
//        this.genero = genero;
//        this.telefone = telefone;
//        this.email = email;
//        this.endereco = endereco;
//        this.dataEntrada = dataEntrada;
//        this.corFaixa = corFaixa;
//    }
}
