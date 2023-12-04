package dad.login;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {

	private StringProperty usuario = new SimpleStringProperty();
	private StringProperty contraseña = new SimpleStringProperty();
	private BooleanProperty ldap = new SimpleBooleanProperty();

	public final StringProperty usuarioProperty() {
		return this.usuario;
	}

	public final String getUsuario() {
		return this.usuarioProperty().get();
	}

	public final void setUsuario(final String nombre) {
		this.usuarioProperty().set(nombre);
	}

	public final StringProperty contraseñaProperty() {
		return this.contraseña;
	}

	public final String getContraseña() {
		return this.contraseñaProperty().get();
	}

	public final void setContraseña(final String contraseña) {
		this.contraseñaProperty().set(contraseña);
	}

	public final BooleanProperty ldapProperty() {
		return this.ldap;
	}

	public final boolean isLdap() {
		return this.ldapProperty().get();
	}

	public final void setLdap(final boolean ldap) {
		this.ldapProperty().set(ldap);
	}

}