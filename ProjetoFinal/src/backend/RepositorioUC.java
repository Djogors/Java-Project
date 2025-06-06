package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUC implements Serializable{
    
    private List<UC> lista;
    //Construtor1 
    public RepositorioUC() {
        lista = new ArrayList<>();
    }
    
    //Construtor2
    public RepositorioUC(int capacidade) {
        lista = new ArrayList<>(capacidade);
    }
    
    //Construtor3
    public RepositorioUC(List<UC> ucs){
        lista = new ArrayList<>(ucs);
    }
    
    //inserir uma só UC
    public void inserir(UC uc) {
        lista.add(uc);
    }
    
    //inserir várias UCs
    public void inserir(List<UC> ucs) {
        lista.addAll(ucs);
    }
    
    //remover uc
    public void remover(UC uc) {
        lista.remove(uc);
    }
    
    //numero de Ucs
    public int getNumeroUCs(){
        return lista.size();
    }
    
    public UC getUC(int num){
        return lista.get(num);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(UC u : lista){
            sb.append("{");
            sb.append(u.toString());
            sb.append("}\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
    //Retorna uma String no formato [{nomeUc1}{nomeUc2}...{nomeUcn}]
    public String toStringName(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(UC u : lista){
            if(u != null){
            sb.append("{");
            sb.append(u.getNome());
            sb.append("}");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String[] toStringArray(){
        int i = 0;
        String[] opcoes = new String[lista.size()];
        for(UC uc : lista){
            opcoes[i] = uc.getNome();
        }
        return opcoes;
    }
    
    public UC findUC(UC uc2){
        for(UC uc : lista){
            if(uc.equals(uc2)) return uc;
        }
        return null;
    }
    
    public UC findUCString(String uc2){
        for(UC uc : lista){
            if(uc.getNome().equals(uc2)) return uc;
        }
        System.out.println("UC nao encontrada!");
        return null;
    }
    
    public UC findUser(String username){
        for(UC p : lista){
            if(p.getNome().equals(username)){
                System.out.println("Curso encontrado.\n");
                return p;
            }
        }
        System.out.println("Curso nao encontrado.\n");
        return null;
    }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }
    
    public void GuardarUCs() throws Exception{
        try (FileOutputStream fos = new FileOutputStream("UCs.ser"); 
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        }
    }
    
    public void LerUCs(){
        try(FileInputStream fileIn = new FileInputStream("UCs.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)){
            lista = (ArrayList<UC>) objectIn.readObject();
        } catch(IOException | ClassNotFoundException e){
            System.err.println("Erro ao ler o ficheiro UCs.ser");
        }
    }
}
