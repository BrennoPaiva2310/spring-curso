package br.com.castgroup.cursos.exception;


public class Exceptions extends RuntimeException {
	
	public Exceptions(String mensagem) {
		super(mensagem);
	}
	
	public Exceptions(String mensagem, Throwable error) {
		super(mensagem, error);
	}

}
