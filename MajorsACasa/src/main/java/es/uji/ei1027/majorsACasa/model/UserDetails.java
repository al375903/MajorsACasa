package es.uji.ei1027.majorsACasa.model;

public class UserDetails {
    String username;
    String password; 
    String tipo;
    
    public UserDetails() {
		super();
	}
    
    public UserDetails(String username, String password, String tipo) {
		super();
		this.username = username;
		this.password = password;
		this.tipo = tipo;
	}

	public String getUsername() {
        return username; 
    }

    public void setUsername(String username) {
        this.username = username; 
    }

    public String getPassword() {
       return password; 
    }

    public void setPassword(String password) {
       this.password = password;
    }
    
    public String getTipo() {
        return tipo; 
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; 
    }
}
