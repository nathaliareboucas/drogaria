package com.drogaria.bean;

//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

@ManagedBean
public class EstadoBean {
	
	public void salvar() {
		/*String texto = "Programação web com Java.";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto);
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, message);*/
		
		Messages.addGlobalInfo("Programação web com java");
	}

}
