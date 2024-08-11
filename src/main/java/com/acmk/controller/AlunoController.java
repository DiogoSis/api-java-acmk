package com.acmk.controller;

import com.acmk.model.Aluno;
import com.acmk.service.AlunoService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AlunoController implements HttpHandler {
    private AlunoService service = new AlunoService();
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            if ("POST".equals(method) && "/aluno".equals(path)) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                Aluno aluno = gson.fromJson(isr, Aluno.class);
                service.cadastrarAluno(aluno);
                String response = "Aluno cadastrado com sucesso!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else if ("GET".equals(method) && path.startsWith("/aluno")) {
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.startsWith("id_aluno=")) {
                    String idStr = query.split("=")[1];
                    UUID id = UUID.fromString(idStr);
                    Optional<Aluno> alunoOpt = service.buscarAlunoPorId(id);
                    String response;
                    if (alunoOpt.isPresent()) {
                        response = gson.toJson(alunoOpt.get());
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                    } else {
                        response = "Aluno não encontrado";
                        exchange.sendResponseHeaders(404, response.getBytes().length);
                    }
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else if ("GET".equals(method) && "/alunos".equals(path)) {
                List<Aluno> alunos = service.buscarTodosAlunos();
                String response = gson.toJson(alunos);
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Método não suportado";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        } catch (SQLException e) {
            String response = "Erro no banco de dados: " + e.getMessage();
            exchange.sendResponseHeaders(500, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
