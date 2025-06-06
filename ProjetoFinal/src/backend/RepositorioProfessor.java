package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProfessor implements Serializable {

    private List<Professor> lista;

    //Construtor1 
    public RepositorioProfessor() {
        lista = new ArrayList<>();
    }

    //Construtor2
    public RepositorioProfessor(int capacidade) {
        lista = new ArrayList<>(capacidade);
    }

    //Construtor3
    public RepositorioProfessor(List<Professor> professores) {
        lista = new ArrayList<>(professores);
    }

    //inserir um só professor
    public void inserir(Professor professor) {
        lista.add(professor);
    }

    //inserir vários professores
    public void inserir(List<Professor> professores) {
        lista.addAll(professores);
    }

    //numero de professores
    public int getNumeroProfessores() {
        return lista.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (Professor p : lista) {
            sb.append("{");
            sb.append(p.toString());
            sb.append("}\n");
        }
        sb.append("]");
        return sb.toString();
    }

    //Retorna uma String no formato [{numProf1}{numProf2}...{numProfn}]
    public String toStringName() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Professor p : lista) {
            sb.append("{");
            sb.append(p.getNum());
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    public Professor findUser(String username) {
        for (Professor p : lista) {
            if (p.getUsername() != null) {
                if (p.getUsername().equals(username)) {
                    return p;
                }
            }
        }
        return null;
    }

    public List<Professor> getProfessores() {
        return lista;
    }

    public void removeProfessor(int num) {
        Professor prof = findUserInt(num);
        if (prof != null) {
            lista.remove(prof);
            System.out.println("Professor removido.\n");
        }
    }

    public void removeProfessor(Professor p) {
        Professor prof = findUserP(p);
        if (prof != null) {
            lista.remove(prof);
            System.out.println("Professor removido.\n");
        }
    }

    public Professor ListarProfAll() {
        for (Professor p : lista) {
            System.out.println(p.toString());
        }
        return null;
    }

    public Professor findUserInt(int num) {
        for (Professor p : lista) {
            if (num == p.getNum()) {

                return p;
            }
        }
        return null;
    }

    public Professor findUserP(Professor p2) {
        for (Professor p : lista) {
            if (p.getNum() == p2.getNum()) {
                return p;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public void GuardarProfessores() throws Exception {
        try (FileOutputStream fos = new FileOutputStream("Professores.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        }
    }

    public void LerProfessores() {
        try (FileInputStream fileIn = new FileInputStream("Professores.ser"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            lista = (ArrayList<Professor>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o ficheiro Professores.ser");
        }
    }
}
