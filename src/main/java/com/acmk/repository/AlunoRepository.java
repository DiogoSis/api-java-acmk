package com.acmk.repository;

import com.acmk.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AlunoRepository {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/karate_academy",
                "postgres",
                "12345678"
        );
    }

    public void salvar(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos (id_aluno, primeiro_nome, sobrenome, data_nascimento, genero, telefone, email, endereco, data_entrada, cor_faixa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, aluno.getId());
            stmt.setString(2, aluno.getPrimeiroNome());
            stmt.setString(3, aluno.getSobrenome());
            stmt.setDate(4, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setString(5, aluno.getGenero());
            stmt.setString(6, aluno.getTelefone());
            stmt.setString(7, aluno.getEmail());
            stmt.setString(8, aluno.getEndereco());
            stmt.setDate(9, new java.sql.Date(aluno.getDataEntrada().getTime()));
            stmt.setString(10, aluno.getCorFaixa());
            stmt.executeUpdate();
        }
    }

    public Optional<Aluno> buscarPorId(UUID id) throws SQLException {
        String sql = "SELECT * FROM alunos WHERE id_aluno = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno(
                            (UUID) rs.getObject("id_aluno"),
                            rs.getString("primeiro_nome"),
                            rs.getString("sobrenome"),
                            rs.getDate("data_nascimento"),
                            rs.getString("genero"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("endereco"),
                            rs.getDate("data_entrada"),
                            rs.getString("cor_faixa")
                    );
                    aluno.setId(id);
                    return Optional.of(aluno);
                }
            }
        }
        return Optional.empty();
    }

    public List<Aluno> buscarTodos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        (UUID) rs.getObject("id_aluno"),
                        rs.getString("primeiro_nome"),
                        rs.getString("sobrenome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("genero"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getDate("data_entrada"),
                        rs.getString("cor_faixa")
                );
                aluno.setId((UUID) rs.getObject("id_aluno"));
                alunos.add(aluno);
            }
        }
        return alunos;
    }
}
