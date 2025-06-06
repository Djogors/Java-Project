package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCurso implements Serializable{
    
    List<Curso> lista;
    
    //Construtor1
    public RepositorioCurso(){
        lista = new ArrayList<>();
    }
    
    //Construtor2
    public RepositorioCurso(int capacidade){
        lista = new ArrayList<>(capacidade);
    }
    
    //Construtor3
    public RepositorioCurso(List<Curso> cursos){
        lista = new ArrayList<>(cursos);
    }
    
    public void inserir(Curso curso){
        lista.add(curso);
    }
    
    public void inserir(List<Curso> cursos){
        lista.addAll(cursos);
    }
    
    public int getNumCursos(){
        return lista.size();
    }
    
    public Curso findUC(UC uc){
        for(Curso c : lista){
            if(c.getUcs().findUC(uc) != null){
                return c;
            }
        }
        System.out.println("Curso nao encontrado.\n");
        return null;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(Curso c : lista){
            sb.append("{");
            sb.append(c.toString());
            sb.append("}\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Curso findCurso(String nome){
        for(Curso a : lista){
            if(a.getDesignacao().equals(nome)){
                System.out.println("Curso encontrado.\n");
                return a;    
            }
        }
        System.out.println("Curso nao encontrado.\n"); 
        return null;
   }
    
    public String toStringAlunos(){
        StringBuilder sb = new StringBuilder();
        for(Curso c : lista){
            sb.append(c.getDesignacao()).append(c.getAlunos().toStringName());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }
    
    public void GuardarCursos() throws Exception{
        try (FileOutputStream fos = new FileOutputStream("Cursos.ser"); 
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        }
    }
    
    public void LerCursos(){
        try(FileInputStream fileIn = new FileInputStream("Cursos.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)){
            lista = (ArrayList<Curso>) objectIn.readObject();
        } catch(IOException | ClassNotFoundException e){
            System.err.println("Erro ao ler o ficheiro Cursos.ser");
        }
    }
}