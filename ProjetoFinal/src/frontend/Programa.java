package frontend;

import backend.Aluno;
import backend.Curso;
import backend.Departamento;
import backend.DiretorCurso;
import backend.Presenca;
import backend.Professor;
import backend.RegenteUC;
import backend.RepositorioAluno;
import backend.RepositorioPresenca;
import backend.RepositorioProfessor;
import backend.RepositorioSumario;
import backend.RepositorioUC;
import backend.Sumario;
import backend.UC;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Programa {

    private final String adminU = "admin";
    private final String adminP = "admin";
    private final Departamento departamento = new Departamento();
    private final Consola consola = new Consola();

    public static void main(String[] args) throws IOException, Exception {
        Programa programa = new Programa();

        int opcao, opcao1;
        String username;
        String password;
        boolean a = false;
        Professor p = null;
        boolean back = false, out = false;
        

        String[] opcoesReg = {
            "Criar sumário",
            "Consultar lista de sumários",
            "Consultar serviço docente",
            "Adicionar alunos ao curso",
            "Remover alunos do curso",
            "Consultar assiduidade de um aluno",
            "Logout"
        };
        String[] opcoesProf = {
            "Criar sumário",
            "Consultar lista de sumários",
            "Consultar serviço docente",
            "Logout"
        };

        String[] opcoesAdmin = {
            "Adicionar/Apagar/Alterar info dos professores",
            "Registar/Alterar info dos cursos/ucs",
            "Listar",
            "Atribuir direcao de curso/ regencia de uc",
            "Logout"
        };

        String[] opcoesDC = {
            "Criar sumário",
            "Consultar lista de sumários",
            "Consultar serviço docente",
            "Alterar designacao de curso",
            "Listar professores",
            "Listar aluno por curso",
            "Logout"
        };
        
        programa.departamento.getProfessores().LerProfessores();
        programa.departamento.getAlunos().LerAlunos();
        programa.departamento.getCursos().LerCursos();
        programa.departamento.getUCs().LerUCs();
        
        //loop infinito
        do {
            //login
            do {
                opcao = programa.menuInicial();

                switch (opcao) {
                    case 1:
                        username = programa.consola.lerString("Nome de Utilizador:");
                        password = programa.consola.lerString("Password:");
                        a = programa.autenticarAdmin(username, password);
                        break;
                    case 2:
                        username = programa.consola.lerString("Nome de Utilizador:");
                        password = programa.consola.lerString("Password:");
                        p = programa.autenticarProf(username, password);
                        break;
                    case 3:
                        //guardar ficheiros
                        programa.departamento.getProfessores().GuardarProfessores();
                        programa.departamento.getAlunos().GuardarAlunos();
                        programa.departamento.getCursos().GuardarCursos();
                        programa.departamento.getUCs().GuardarUCs();
                        
                        out = true;
                        break;
                }
            } while (p == null && a == false && out == false);

            if (p instanceof RegenteUC) {
                do {
                    opcao1 = programa.consola.lerInteiro(opcoesReg);

                    switch (opcao1) {
                        case 1: {
                            if(!p.getUcs().isEmpty()){
                                //Menu
                                UC uc = programa.menuUCs(p);
                                //Curso da UC
                                Curso curso = programa.departamento.getCursos().findUC(uc);
                                //Criar Sumário
                                if(curso != null){
                                    programa.criarSumario(uc, curso);
                                }
                                else{
                                    programa.consola.escreverErro("UC nao inserida no curso");
                                }
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }
                            break;
                        }
                        case 2: {
                            if(!p.getUcs().isEmpty()){
                                //Menu UCs
                                UC uc = programa.menuUCs(p);
                                //Menu Tipo de Aula
                                String tipo = programa.menuTipo();
                                //Listar
                                programa.consola.escrever(uc.getSumarios().toStringTipoUC(tipo));
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }

                            break;
                        }
                        case 3: {
                            if(!p.getUcs().isEmpty()){
                                programa.consola.escrever(p.getUcs().toString());
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }

                            break;
                        }
                        case 4: {
                            programa.adicionarAlunoC();

                            break;
                        }
                        case 5: {
                            programa.removerAlunoC((RegenteUC) p);

                            break;
                        }
                        case 6: {
                            programa.verificarAssiduidade((RegenteUC) p);

                            break;
                        }
                        case 7: {

                            back = true;
                            break;
                        }
                    }
                } while (back == false);
            } else if (p instanceof DiretorCurso) {
                do {
                    opcao1 = programa.consola.lerInteiro(opcoesDC);

                    switch (opcao1) {
                        case 1: {
                            if(!p.getUcs().isEmpty()){
                                //Menu
                                UC uc = programa.menuUCs(p);
                                //Curso da UC
                                Curso curso = programa.departamento.getCursos().findUC(uc);
                                //Criar Sumário
                                programa.criarSumario(uc, curso);
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }

                            break;
                        }
                        case 2: {
                            if(!p.getUcs().isEmpty()){
                                //Menu UCs
                                UC uc = programa.menuUCs(p);
                                //Menu Tipo de Aula
                                String tipo = programa.menuTipo();
                                //Listar
                                programa.consola.escrever(uc.getSumarios().toStringTipoUC(tipo));
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }

                            break;
                        }
                        case 3: {
                            if(!p.getUcs().isEmpty()){
                                programa.consola.escrever(p.getUcs().toString());
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }

                            break;
                        }
                        case 4: {
                            String novoNome = programa.consola.lerString("Introduza o novo nome do curso:");
                            ((DiretorCurso) p).getCurso().setDesginacao(novoNome);
                            break;
                        }
                        case 5: {
                            //Numero de professores
                            int num = programa.departamento.getProfessores().getNumeroProfessores();
                            System.out.println("Numero de professores: " + num);

                            break;
                        }
                        case 6: {
                            //Listar alunos por curso
                            programa.consola.escrever(programa.departamento.getCursos().toStringAlunos());
                            break;
                        }
                        case 7: {

                            back = true;
                            break;
                        }
                    }
                } while (back == false);
            } else if (p instanceof Professor) {
                do {
                    opcao1 = programa.consola.lerInteiro(opcoesProf);

                    switch (opcao1) {
                        case 1: {
                            if(!p.getUcs().isEmpty()){
                                //Menu
                                UC uc = programa.menuUCs(p);
                                //Curso da UC
                                Curso curso = programa.departamento.getCursos().findUC(uc);
                                //Criar Sumário
                                programa.criarSumario(uc, curso);
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }
                            
                            break;
                        }
                        case 2: {
                            if(!p.getUcs().isEmpty()){
                                //Menu UCs
                                UC uc = programa.menuUCs(p);
                                //Menu Tipo de Aula
                                String tipo = programa.menuTipo();
                                //Listar
                                programa.consola.escrever(uc.getSumarios().toStringTipoUC(tipo));
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }

                            break;
                        }
                        case 3: {
                            if(!p.getUcs().isEmpty()){
                                programa.consola.escrever(p.getUcs().toString());
                            } else {
                                programa.consola.escreverErro("UCs vazias");
                            }
                            
                            break;
                        }
                        case 4: {

                            back = true;
                            break;
                        }
                    }
                } while (back == false);
            }
            else if (a == true){
                do{
                    opcao1 = programa.consola.lerInteiro(opcoesAdmin);
                    
                    switch(opcao1) {
                        
                        case 1:{
                            programa.infoProf();
                        
                            break;
                        }
                        case 2:{
                            programa.infoCursoUC();
                            
                            break;
                        }
                        case 3:{
                            programa.listar();
                            
                            break;
                        }
                        case 4:{
                            programa.atribuir();
                            
                            break;
                        }
                        case 5:{
                            back = true;
                            
                            break;
                        }
                    }
                } while (back == false);
            }
            
            a = false;
            p = null;
            back = false;
        } while (out == false);
    }

    public int menuInicial() {
        int opcao;
        do {
            opcao = consola.lerInteiro("1)Administrador\n2)Professor\n3)Sair");
        } while (opcao < 1 || opcao > 3);
        return opcao;
    }

    public Professor autenticarProf(String username, String password) {
        Professor p = departamento.getProfessores().findUser(username);
        if (p == null) {
            consola.escreverErro("Utilizador não encontrado!");
        } else {
            if (p.getPassword().equals(password)) {
                consola.escrever("Autenticado com sucesso!");
            }
        }
        return p;
    }

    public boolean autenticarAdmin(String username, String password) {
        if (username.equals(adminU) && password.equals(adminP)) {
            return true;
        } else {
            return false;
        }
    }

    public UC menuUCs(Professor p) {
            RepositorioUC ucs = p.getUcs();
            String[] opcoes = ucs.toStringArray();
            int opcao = consola.lerInteiro(opcoes);
            return ucs.getUC(opcao-1);
    }

    public void criarSumario(UC uc, Curso curso) {
        String titulo = consola.lerString("Introduza o titulo:");
        String tipo;
        tipo = consola.lerTipo("Introduza o tipo:");
        String texto = consola.lerString("Introduza o texto:");
        LocalDateTime dataHora = consola.lerDataHora("Introduza a data e a hora no formato");
        RepositorioPresenca presencas = lerPresencas("Introduza o numero mecanografico dos alunos presentes (um de cada vez):", curso);

        Sumario sum = new Sumario(titulo, tipo, texto, dataHora, presencas);

        uc.inserirSumario(sum);
    }

    public RepositorioPresenca lerPresencas(String mensagem, Curso curso) {
        int num;
        //iniciar todas as presenças a falso
        RepositorioAluno alunos = curso.getAlunos();
        RepositorioPresenca presencas = alunos.iniciarPresencas();
        Presenca p;

        //meter verdadeiras as que o utilizador intencionar
        do {
            consola.escrever("Para SAIR introduza o numero 0");
            num = consola.lerInteiro(mensagem);
            p = presencas.findAlunoNum(num);
            if (p == null) {
                consola.escreverErro("Aluno não encontrado.");
            } else {
                p.setPresente(true);
            }
        } while (num != 0);
        return presencas;
    }

    public String menuTipo() {
        String[] opcoes = {
            "T",
            "TP",
            "PL"
        };
        int opcao = consola.lerInteiro(opcoes);
        return opcoes[opcao-1];
    }

    public void removerAlunoC(RegenteUC p) {
        int num;
        Curso curso;
        Aluno a;
        //ler String
        num = consola.lerInteiro("Introduza o numero do aluno que pretende remover:");
        //verificar se o aluno existe no Curso
        curso = departamento.getCursos().findUC(p.getUC());
        if (curso != null) {
            a = curso.getAlunos().findNum(num);
            if (a != null) {
                curso.getAlunos().remover(a);
            }
        }
    }

    public void adicionarAlunoC() {
        int num;
        Curso curso;
        Aluno a;
        String lercurso = consola.lerString("Introduza o nome do curso");
        curso = departamento.getCursos().findCurso(lercurso);
        if (curso != null) {
                String nome = consola.lerString("Introduza o nome do aluno:");
                do{
                    num = consola.lerInteiro("Introduza o numero do aluno que pretende adicionar:");
                }while(departamento.getAlunos().findNum(num)!= null);
                a = new Aluno(num,nome,curso);
                curso.getAlunos().inserir(a);
        }
    }

    public void verificarAssiduidade(RegenteUC p) {
        int num;
        Curso curso;
        Aluno a;
        //ler String
        num = consola.lerInteiro("Introduza o numero do aluno:");
        //verificar se o aluno existe no Curso
        curso = departamento.getCursos().findUC(p.getUC());
        if (curso != null) {
            a = curso.getAlunos().findNum(num);
            if (a != null) {
                consola.escrever(p.getUC().getSumarios().listarAssiduidade(num));
            }
        }
    }
    
    public void infoProf(){
        int opcao;
        Professor p;
        int num;
        
        String[] opcoes = {
            "Adicionar professor",
            "Apagar professor",
            "Alterar informacao dos professores"
        };
        
        opcao = consola.lerInteiro(opcoes);
        switch (opcao){
            case 1:
                Professor pAdd = createProf();
                departamento.getProfessores().inserir(pAdd);
                
                break;
            case 2:
                num = consola.lerInteiro("Introduza o num do professor");
                departamento.getProfessores().removeProfessor(num);
                    
                break;
            case 3:
                Professor pChange = departamento.getProfessores().findUserInt(consola.lerInteiro("Introduza o num do professor:"));
                if(pChange != null){
                    changeInfoProf(pChange);
                }

                break;
        }
    }
    
    public Professor createProf(){
        int opcao;
        String ucNome;
        UC uc;
        Professor p;
        String username;
        int num;
        do{
            username = consola.lerString("Introduza o novo username do professor:");
        }while(departamento.getProfessores().findUser(username) != null);
        String password = consola.lerString("Introduza a nova password do professor:");
        String nome = consola.lerString("Introduza o novo nome do professor:");
        do{
            num = consola.lerInteiro("Introduza o novo num do professor:");
        }while(departamento.getProfessores().findUserInt(num) != null || num < 1);
        LocalDate inicioFuncoes = consola.lerData("Introduza a nova data inicioFuncoes:");
        RepositorioUC ucs = new RepositorioUC();
        do{
            opcao = consola.lerInteiro("Deseja introduzir uma nova UC?\n1-Sim\n2-Nao");
            if(opcao == 1){
                ucNome = consola.lerString("Introduza a designacao da UC:");
                uc = departamento.getUCs().findUCString(ucNome);
                if(uc != null){
                    if(ucs.findUC(uc) == null)
                        ucs.inserir(uc);
                }
            }
        }while(opcao != 2);
        
        p = new Professor(username, password, nome, num, inicioFuncoes, ucs);
        return p;
    }
    
    public void changeInfoProf(Professor p){
        int opcao;
        boolean back = false;
        UC uc;
        
        String[] opcoes = {
            "Nome",
            "Num",
            "InicioFuncoes",
            "UCs",
            "Voltar"
        };
        
        do{
            opcao = consola.lerInteiro(opcoes);
            
            switch (opcao){
                case 1:{
                    p.setNome(consola.lerString("Introduza o novo nome:"));
                    break;
                }
                case 2:{
                    int num;
                    do{
                        num = consola.lerInteiro("Introduza o novo num:");
                    }while(departamento.getProfessores().findUserInt(num) != null || num < 1);
                    p.setNum(num);
                    break;
                }
                case 3:{
                    p.setData(consola.lerData("Introduza a nova data"));
                    break;
                }
                case 4:{
                    int opcao2;
                    
                    String[] opcoes2 = {
                        "Adicionar UC",
                        "Apagar UC"
                    };
                    
                    opcao2 = consola.lerInteiro(opcoes2);
                    
                    if(opcao2 == 1){
                        uc = departamento.getUCs().findUCString(consola.lerString("Introduza o nome da uc para adicionar:"));
                        p.getUcs().inserir(uc);
                    }
                    else{
                        uc = p.getUcs().findUCString(consola.lerString("Introduza o nome da uc para eliminar:"));
                        p.getUcs().remover(uc);
                    }
                    
                    break;
                }
                case 5:{
                    back = true;
                    break;
                }
            }
        }while(back == false);
    }
    
    public UC createUC(){
        int opcao;
        Professor p;
        RepositorioProfessor equipa = new RepositorioProfessor();
        RepositorioSumario sumario = new RepositorioSumario();
        RegenteUC reg = new RegenteUC();
        
        UC uc = new UC();
        String nome = consola.lerString("Introduza o novo nome: ");
        uc.setNome(nome);
        uc.setEquipa(equipa);
        do{
            opcao = consola.lerInteiro("Deseja introduzir um novo professor:\n1-Sim\n2-Nao");
            if(opcao == 1){
                p = departamento.getProfessores().findUserInt(consola.lerInteiro("Introduza o num do professor:"));
                if(p != null && uc.getEquipa().findUserP(p) == null){
                    uc.getEquipa().inserir(p);
                    p.getUcs().inserir(uc);
                }
            }
        }while(opcao != 2);
        uc.setSumarios(sumario);
        uc.setRegente(reg);
        return uc;
    }
    
    public void changeInfoUC(UC uc){
        int opcao;
        boolean back = false;
        
        String[] opcoes = {
            "Nome",
            "Remover RegenteUC",
            "Equipa",
            "Remover Sumario",
            "Voltar"
        };
        
        do{
            opcao = consola.lerInteiro(opcoes);
            switch(opcao){
                case 1:{
                    uc.setNome(consola.lerString("Introduza o novo nome:"));
                    break;
                }
                case 2:{
                    RegenteUC reg = uc.getRegente();
                    uc.setRegente(null);
                    Professor p = new Professor(reg.getUsername(), reg.getPassword(), reg.getNome(), reg.getNum(), reg.getData(), reg.getUcs());
                    departamento.getProfessores().removeProfessor(reg);
                    departamento.getProfessores().inserir(p);
                    
                    break;
                }
                case 3:{
                    int opcao2;
                    
                    String[] opcoes2 = {
                        "Adicionar Professor",
                        "Remover Professor"
                    };
                    
                    opcao2 = consola.lerInteiro(opcoes2);
                    if(opcao2 == 1){
                        Professor p = departamento.getProfessores().findUserInt(consola.lerInteiro("Introduza o numero do professor:"));
                        if(p != null && uc.getEquipa().findUserP(p) == null){
                            uc.getEquipa().inserir(p);
                            p.getUcs().inserir(uc);
                            consola.escrever("Professor adicionado a equipa.\n");
                        }
                    }
                    else{
                        Professor p = uc.getEquipa().findUserInt(consola.lerInteiro("Introduza o numero do professor:"));
                        if(p != null){
                            uc.getEquipa().removeProfessor(p);
                            p.getUcs().remover(uc);
                            consola.escrever("Professor removido da equipa.\n");
                        }
                    }
                    
                    break;
                }
                case 4:{
                    Sumario s = uc.getSumarios().find(consola.lerString("Introduza o titulo do sumario"));
                    if(s != null){
                        uc.getSumarios().remover(s);
                    }
                    
                    break;
                }
                case 5:{
                    back = true;
                    break;
                }
            }
        }while(back == false);
    }
    
    public Curso createCurso(){
        int opcao;
        Curso curso = new Curso();
        String nome = consola.lerString("Introduza o novo nome: ");
        curso.setDesginacao(nome);
        RepositorioAluno alunos = new RepositorioAluno();
        curso.setAlunos(alunos);
        RepositorioUC ucs = new RepositorioUC();
        DiretorCurso dc = new DiretorCurso();
        curso.setDiretor(dc);
        curso.setUcs(ucs);
        do{
            opcao = consola.lerInteiro("Deseja introduzir um novo aluno:\n1-Sim\n2-Nao");
            if(opcao == 1){
                Aluno a = departamento.getAlunos().findNum(consola.lerInteiro("Introduza o num do aluno:"));
                if(a != null && curso.getAlunos().findAluno(a) == null){
                    curso.getAlunos().inserir(a);
                }
            }
        }while(opcao != 2);
        do{
            opcao = consola.lerInteiro("Deseja introduzir uma nova UC:\n1-Sim\n2-Nao");
            if(opcao == 1){
                UC uc = departamento.getUCs().findUCString(consola.lerString("Introduza uma nova UC:"));
                if(uc != null && curso.getUcs().findUC(uc) == null){
                    curso.getUcs().inserir(uc);
                }
            }
        }while(opcao != 2);
        
        return curso;
    }
    
    public void changeInfoCurso(Curso curso){
        int opcao;
        boolean back = false;
        
        String[] opcoes = {
            "Nome",
            "Remover DiretorCurso",
            "Alunos",
            "UCs",
            "Voltar"
        };
        
        do{
            opcao = consola.lerInteiro(opcoes);
            switch(opcao){
                case 1:{
                    curso.setDesginacao(consola.lerString("Introduza o novo nome:"));
                    break;
                }
                case 2:{
                    DiretorCurso dc = curso.getDiretor();
                    curso.setDiretor(null);
                    Professor p = new Professor(dc.getUsername(), dc.getPassword(), dc.getNome(), dc.getNum(), dc.getData(), dc.getUcs());
                    departamento.getProfessores().removeProfessor(dc);
                    departamento.getProfessores().inserir(p);
                    
                    break;
                }
                case 3:{
                    int opcao2;
                    
                    String[] opcoes2 = {
                        "Adicionar aluno",
                        "Remover aluno"
                    };
                    
                    opcao2 = consola.lerInteiro(opcoes2);
                    
                    if(opcao2 == 1){
                        Aluno a = departamento.getAlunos().findNum(consola.lerInteiro("Introduza o numero do aluno:"));
                        if(a != null && curso.getAlunos().findAluno(a) == null){
                            curso.getAlunos().inserir(a);
                        }
                    }
                    else{
                        Aluno a = curso.getAlunos().findNum(consola.lerInteiro("Introduza o numero do aluno:"));
                        if(a != null){
                            curso.getAlunos().remover(a);
                        }
                    }
                    
                    break;
                }
                case 4:{
                    int opcao2;
                    
                    String[] opcoes2 = {
                        "Adicionar UC",
                        "Remover UC"
                    };
                    
                    opcao2 = consola.lerInteiro(opcoes2);
                    
                    if(opcao2 == 1){
                        UC uc = departamento.getUCs().findUCString(consola.lerString("Introduza o nome da uc:"));
                        if(uc != null && curso.getUcs().findUC(uc) == null){
                            curso.getUcs().inserir(uc);
                        }
                    }
                    else{
                        UC uc = curso.getUcs().findUCString(consola.lerString("Introduza o nome da uc"));
                        if(uc != null){
                            curso.getUcs().remover(uc);
                        }
                    }
                
                    break;
                }
                case 5:{
                    back = true;
                    break;
                }
            }
        }while(back == false);
    }
    
    public void listar(){
        int opcao;
        boolean back = false;
        
        String[] opcoes = {
            "Listar Cursos",
            "Listar UCs",
            "Listar Alunos",
            "Listar Professores",
            "Voltar"
        };
        do{
            opcao = consola.lerInteiro(opcoes);
            switch(opcao){
                case 1:{
                    consola.escrever(departamento.getCursos().toString());
                    break;
                }
                case 2:{
                    consola.escrever(departamento.getUCs().toString());
                    break;
                }
                case 3:{
                    consola.escrever(departamento.getAlunos().toString());
                    break;
                }
                case 4:{
                    consola.escrever(departamento.getProfessores().toString());
                    break;
                }
                case 5:{
                    back = true;
                    break;
                }
            }
        }while(back == false);
    }
    
    public void infoCursoUC(){
        int opcao;
        boolean back = false;
        
        String[] opcoes = {
            "Registar UC",
            "Alterar info UC",
            "Registar Curso",
            "Alterar info Curso",
            "Voltar"
        };
        
        do{
            opcao = consola.lerInteiro(opcoes);
            switch(opcao){
                case 1:{
                    UC uc = createUC();
                    departamento.getUCs().inserir(uc);
                    
                    break;
                }
                case 2:{
                    UC uc = departamento.getUCs().findUCString(consola.lerString("Introduza o nome da UC:"));
                    if(uc != null){
                    changeInfoUC(uc);
                    }
                    break;
                }
                case 3:{
                    Curso curso = createCurso();
                    departamento.getCursos().inserir(curso);
                    
                    break;
                }
                case 4:{
                    Curso curso = departamento.getCursos().findCurso(consola.lerString("Introduza o nome do curso:"));
                    if(curso != null){
                    changeInfoCurso(curso);
                    }
                }
                case 5:{
                
                    back = true;
                    break;
                }
            }
        }while(back == false);
    }
    
    public void atribuirDirecao(){
        Professor p;
        Curso curso;
        
        p = departamento.getProfessores().findUserInt(consola.lerInteiro("Introduza o num do professor: "));
        if(p != null){
            if(p instanceof DiretorCurso || p instanceof RegenteUC){
                consola.escreverErro("Professor ja diretor/regente");
            } else{
                String nome = p.getNome();
                int num = p.getNum();
                LocalDate data = p.getData();
                String username = p.getUsername();
                String password = p.getPassword();
                RepositorioUC ucs = p.getUcs();

                curso = departamento.getCursos().findCurso(consola.lerString("Introduza o nome do curso:"));

                if(curso != null){
                    departamento.getProfessores().removeProfessor(p);
                    DiretorCurso dc = new DiretorCurso(username, password, nome, num, data, ucs, curso);
                    curso.setDiretor(dc);
                    departamento.getProfessores().inserir(dc);
                }
            }
        }
    }
    
    public void atribuirRegencia(){
        Professor p;
        UC uc;
        p = departamento.getProfessores().findUserInt(consola.lerInteiro("Introduza o num do professor: "));
        if(p != null){
            if(p instanceof RegenteUC || p instanceof DiretorCurso){
                consola.escreverErro("Professor ja regente/diretor");
            } else {
                String nome = p.getNome();
                int num = p.getNum();
                LocalDate data = p.getData();
                String username = p.getUsername();
                String password = p.getPassword();
                RepositorioUC ucs = p.getUcs();

                if(!ucs.isEmpty()){
                    uc = menuUCs(p);

                    departamento.getProfessores().removeProfessor(p);
                    RegenteUC reg = new RegenteUC(username, password, nome, num, data, ucs, uc);
                    departamento.getProfessores().inserir(reg);
                    uc.setRegente(reg);
                } else {
                    consola.escrever("UCs vazias");
                }
            }
        }
    }
    
    public void atribuir(){
        int opcao;
        boolean back = false;
        
        String[] opcoes = {
            "Atribuir direcao",
            "Atribuir regencia",
            "Voltar"
        };
        
        do{
            opcao = consola.lerInteiro(opcoes);
            
            switch (opcao){
                case 1:{
                    atribuirDirecao();
                    break;
                }
                case 2:{
                    atribuirRegencia();
                    break;
                }
                case 3:{
                    back = true;
                    break;
                }
            }
        }while(back == false);
    }
}
