package es.informatica.uma.sii.proyectog.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Saludo {
	public String getSaludo() {
		return "K pasa xavale";
	}
}