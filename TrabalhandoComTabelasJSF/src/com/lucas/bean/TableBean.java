package com.lucas.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import com.lucas.model.Despesa;

@SuppressWarnings("serial")
@Named("table")
@SessionScoped
public class TableBean implements Serializable{
	
	private List<Despesa> despesasList = new ArrayList<>();
	private ListDataModel<Despesa> despesas = new ListDataModel<>(despesasList);
	
//	public TableBean() {
//		Despesa d = new Despesa();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			d.setData(sdf.parse("28/01/1994"));
//		} catch (ParseException e) {
//		}
//		
//		d.setDescricao("Item qualquer");
//		d.setEdit(false);
//		d.setValor(135.0);
//		
//		despesasList.add(d);
//		
//		d = new Despesa();
//		
//		sdf = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			d.setData(sdf.parse("28/01/2000"));
//		} catch (ParseException e) {
//		}
//		
//		d.setDescricao("Item qualquer 2");
//		d.setEdit(false);
//		d.setValor(1000.0);
//		
//		despesasList.add(d);
//	}
	
	public String inserir() {
		Despesa d = new Despesa();
		d.setEdit(true);
		
		despesasList.add(d);
		
		return null;
	}
	
	public String editar(Despesa despesa) {
		despesa.setEdit(true);
		return null;
	}
	
	public String gravar(Despesa despesa) {
		despesa.setEdit(false);
		return null; //Não é necessário salvar o objeto porque ao realizar a operação, os dados já são enviados para o servidor.
	}
	
	public String excluir(Despesa despesa) {
		despesasList.remove(despesa);
		return null;
	}
	
	public ListDataModel<Despesa> getDespesas() {
		return despesas;
	}
	
}
