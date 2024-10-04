package com.teste.consultaescola.model;

import com.teste.consultaescola.enums.Curso;
import com.teste.consultaescola.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="nome")
    @Size(min = 5, max = 35, message = "O nome de conter no mínimo 5 caracteres.")
    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @Column(name="curso")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo curso não pode ser nulo.")
    private Curso curso;

    @Column(name="matricula")
    @NotBlank(message = "Clique no botão para gerar a matrícula")
    private String matricula;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo status não pode ser nulo.")
    private Status status;

    @Column(name="turno")
    @NotBlank(message = "O campo turno não pode ser vazio.")
    @Size(min = 4, max = 5, message = "O turno deve ser 'Manhã', 'Tarde' ou 'Noite'.")
    private String turno;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
