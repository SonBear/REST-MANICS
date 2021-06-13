package com.manics.rest.exception;

public class UsuarioRegistradoException extends BadRequestException{
    public UsuarioRegistradoException(String usuario) {
        //super(String.format("El alumno con la matricula: %d ya se encuentra registrado", matricula));
        super("El usuario "+ usuario+" ya se encuentra registrado");
    }
}
