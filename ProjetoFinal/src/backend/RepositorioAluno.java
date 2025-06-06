package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAluno implements Serializable{
    
    private List<Aluno> lista;
    
    //Construtor1 
    public RepositorioAluno() {
        lista = new ArrayList<>();
    }
    
    //Construtor2
    public RepositorioAluno(int capacidade) {
        lista = new ArrayList<>(capacidade);
    }
    
    //Construtor3
    public RepositorioAluno(List<Aluno> alunos){
        lista = new ArrayList<>(alunos);
    }
    
    //inserir um só aluno
    public void inserir(Aluno aluno) {
        lista.add(aluno);
    }
    
    //inserir vários alunos
    public void inserir(List<Aluno> alunos) {
        lista.addAll(alunos);
    }
    
    public void remover(Aluno a){
        lista.remove(a);
    }
    
    //numero de alunos
    public int getNumeroAlunos(){
        return lista.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(Aluno a : lista){
            sb.append("{");
            sb.append(a.toString());
            sb.append("}\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
    //Retorna uma String no formato [{numAlu1}{numAlu2}...{numAluN}]
    public String toStringName(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Aluno a : lista){
            sb.append("{");
            sb.append(a.getNum());
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public RepositorioPresenca iniciarPresencas(){
        RepositorioPresenca presencas = new RepositorioPresenca();
        Presenca p;
        for(Aluno a : lista){
            p = new Presenca(a, false);
            presencas.inserir(p);
        }
        return presencas;
    }
    
    public Aluno findNum(int Num){
        for(Aluno a : lista){
            if(a.getNum() == Num){
                return a;
            }
        }
       System.out.println("Aluno nao encontrado.\n"); 
       return null;
    }
    
    public Aluno findAluno(Aluno a){
        for(Aluno b : lista){
            if(b.getNum() == a.getNum()){
                return b;
            }
        } 
       return null;
    }
    
    public Aluno ListarAlunoAll(){
         for(Aluno a : lista){
             System.out.println(a.toString());
         }
        return null;
     }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }
    
    public void GuardarAlunos() throws Exception{
        try (FileOutputStream fos = new FileOutputStream("Alunos.ser"); 
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        }
    }
    
    public void LerAlunos(){
        try(FileInputStream fileIn = new FileInputStream("Alunos.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)){
            lista = (ArrayList<Aluno>) objectIn.readObject();
        } catch(IOException | ClassNotFoundException e){
            System.err.println("Erro ao ler o ficheiro Alunos.ser");
        }
    }
}
