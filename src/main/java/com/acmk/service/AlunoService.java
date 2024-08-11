package com.acmk.service;

import com.acmk.model.Aluno;
import com.acmk.repository.AlunoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AlunoService {
    private AlunoRepository repository = new AlunoRepository();

    public void cadastrarAluno(Aluno aluno) throws SQLException {
        aluno.setId(UUID.randomUUID());
        repository.salvar(aluno);
    }

    public Optional<Aluno> buscarAlunoPorId(UUID id) throws SQLException {
        return repository.buscarPorId(id);
    }

    public List<Aluno> buscarTodosAlunos() throws SQLException {
        return repository.buscarTodos();
    }
}