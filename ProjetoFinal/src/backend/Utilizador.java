package backend;

import java.io.Serializable;

public abstract class Utilizador implements Serializable{
    private String username;
    private String password;
    
    public Utilizador(){}
    
    public Utilizador (String novoUsername, String novaPassword){
        username = novoUsername;
        password = novaPassword;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String novoUsername){
        username = novoUsername;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String novaPassword){
        password = novaPassword;
    }
}
