package com.snackhub.domain;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Customer{

    private final Long id;
    private final String cpf;

    public Customer(Long id, String cpf) {
        this.id = id;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
