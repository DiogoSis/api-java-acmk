package com.acmk;

import com.acmk.controller.AlunoController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/aluno", new AlunoController());
        server.start();
        System.out.println("Server started on port 8000");
    }
}