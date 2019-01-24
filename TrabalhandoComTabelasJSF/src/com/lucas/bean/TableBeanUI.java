package com.lucas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.lucas.model.Despesa;


/*
 * 
 * Essa classe � focada no elemento UIRepeat.
 * Devido a isso, ela n�o trabalha com o DataModel.
 * 
 * */

@SuppressWarnings("serial")
@Named("tableUI")
@SessionScoped
public class TableBeanUI implements Serializable{
	
	private List<Despesa> despesas = new ArrayList<>();
	
	
	public String inserir() {
		Despesa d = new Despesa();
		d.setEdit(true);
		
		despesas.add(d);
		
		return null;
	}
	
	public String editar(Despesa despesa) {
		despesa.setEdit(true);
		return null;
	}
	
	public String gravar(Despesa despesa) {
		despesa.setEdit(false);
		return null; //N�o � necess�rio salvar o objeto porque ao realizar a opera��o, os dados j� s�o enviados para o servidor.
	}
	
	public String excluir(Despesa despesa) {
		despesas.remove(despesa);
		return null;
	}
	
	public List<Despesa> getDespesas() {
		return despesas;
	}
	
}
