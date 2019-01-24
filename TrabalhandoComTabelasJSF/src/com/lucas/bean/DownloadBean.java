package com.lucas.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@Named("downloadBean")
@RequestScoped
public class DownloadBean implements Serializable{

	@Inject
	private FacesContext facesContext;
	@Inject
	private ExternalContext externalContext;
	
	public String download() {
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		/* As informa��es setadas no HTTP de resposta est�o contidas nas especifica��o do protocolo, n�o s�o inventadas */
		response.setHeader("Content-Disposition", "attachment;filename=\"arquivo.pdf\""); //Setando valores no cabe�alho do protocolo HTTP de resposta. O attachment informa que ser� uma fun��o de download.
		//Caso essa linha seja comentada, por padr�o, ser� usado o Content-Disposition-Inline, ou seja, o documento ser� aberto no pr�prio navegador.
		
		response.setContentType("application/pdf");
		
		int totalBytes;
		
		//Como quero que o InputStream seja fechado no final do processo, coloco o Try With Resources
		try(InputStream in = externalContext.getResourceAsStream("/WEB-INF/Avalia��o_analista_tecnico.pdf")){
			OutputStream out = response.getOutputStream();
			
			totalBytes = copy(in, out);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
		response.setContentLength(totalBytes); //Informando para o navegador o total de bytes que ser�o transferidos.
		
		facesContext.responseComplete();
		
		return null;
	}
	
	private int copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		int totalBytes = 0;
		
		while(true) {
			len = in.read(buffer);
			if(len < 0) {
				break;
			}
			totalBytes += len;
			out.write(buffer, 0, len);
		}
		
		return totalBytes;
	}
	
}
