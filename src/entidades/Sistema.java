/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author teclab
 */
public class Sistema {

    private ArrayList<Pessoa> pessoa = new ArrayList<>();//Arrey de pessoas
    private ArrayList<Banco> banco = new ArrayList<>();//ArrayList de bancos
    Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        //Chama o menu principal 
        Sistema s = new Sistema();
        s.menuPrincipal();
    }

    //metodo que exibe o menu principal do sistema;
    public void menuPrincipal() {
        while (true) {
            System.out.println("\n:: S I S T E M A  B A N C Á R I O::");
            System.out.println("Bem vindo(a) ao sistema. Escolha a opção desejada");
            System.out.println("1 - Administrar o Sistema");
            System.out.println("2 - Acessar como Cliente");
            System.out.println("3 - Sair");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    menuAdministrarSistema();//chama menu de Administrador
                    break;
                case 2:
                    menuCliente();//Chama menu do cliente;
                    break;
                case 3:
                    System.out.println("\nObrigado por usar o Sistema Bancario\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nopção inválida\n");
            }
        }
    }

    //menu que permite adiministra o sistema
    public int menuAdministrarSistema() {
        while (true) { //Exibe continuamente o menu de opções;
            System.out.println("\n:: A D M I N S T R A Ç Ã O  D O  S I T E M A::\n");
            System.out.println("Escola a opção desejada ");
            System.out.println("1 - Gerenciar Bancos");
            System.out.println("2 - Gerenciar Pessoas");
            System.out.println("3 - Gerenciar Agências");
            System.out.println("4 - Gerenciar Contas");
            System.out.println("5 - Voltar Menu Anterior");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao) {
                case 1:// vamos gerenciar banco
                    menuGerenciarBancos();
                    break;
                case 2: // vamos gerenciar os as pessoas(que serão Futuroos clientes
                    menuGerenciarPessoas();
                    break;
                case 3: // vamos gerenciar as contas de uma determinada agência de um determinado banco
                    menuGerenciarAgencias();
                    break;
                case 4: // vamos gerenciar as contas de uma determinada agência de um determinado banco
                    menuGerenciarContas();
                    break;
                case 5:
                    return 1; // voltar para o menu Principal
                default:
                    throw new AssertionError();

            }
        }
    }

    //menu cadastra, Listar, excluir e atualizar
    public int menuGerenciarBancos() {
        Banco temp; //serve para várias operações neste menu
        String pesquisaBanco; //serve para as pesquisas dos bancos
        while (true) { //mostra o menu de forma repetitiva até o usuário usar a opção de sair
            System.out.println("\n :: G E R E N C I A R  B A N C O S::\n");
            System.out.println("Escolha a opção desejada");
            System.out.println("1 - Novo Banco");
            System.out.println("2 - Listas Bancos");
            System.out.println("3 - Pesquisar Bancos");
            System.out.println("4 - Excluir Bancos");
            System.out.println("5 - Atualizar Banco");
            System.out.println("6 - Voltar ao menur anteiro");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine()); //lê a opção do usuario

            switch (opcao) {
                case 1: //vamos cadastra um novo banco
                    System.out.println("\nNúmero do Banco: ");
                    String numeroBanco = entrada.nextLine();
                    // vamos incrementar o contador de bancos
                    Banco.contadorBancos++;

                    //agora vamos criar um novo objeto da classe Banco
                    Banco b = new Banco(Banco.contadorBancos,nomeBanco, numeroBanco);
                    // e adicionamos no ArrayListe de bancos
                    banco.add(b);
                    // e finalmemnte mostramos uma mensagem de sucesso.
                    System.out.println("\nO Banco foi criado com sucesso!!!");
                    break;
                case 2://vamos listar os bancos cadastrados
                    if (banco.isEmpty()) {
                        System.out.println("\nNão há nenhum banco cadastrado.");
                    } else {
                        for (int i = 0; i < banco.size(); i++) {
                            temp = banco.get(i);//obtém o banco da iteração atual
                            System.out.println("\nId: " + temp.getId());
                            System.out.println("\nNúmero: " + temp.getNumero());
                            System.out.println("\nNome: " + temp.getNome());
                            System.out.println("\nQuantas agéncias: " + temp.getAgencias());
                        }
                    }
                    break;
                case 3:// vamos pesquiar um banco
                    System.out.println("\nInforme o id, número ou nome do Banco");
                    pesquisaBanco = entrada.nextLine();
                    //chama o método que pesquisa o banco
                    temp = pesquisaBanco(pesquisaBanco);
                    if (temp == null) {//banco não encontrado
                        System.out.println("\nO banco não foi encontrado.:/");
                    } else {
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNúmero: " + temp.getNumero());
                        System.out.println("\nNome: " + temp.getNome());
                        System.out.println("\nQuant Agência: " + temp.getAgencias());
                    }
                    break;
                case 4://vamos excluir um banco
                    System.out.println("\nIndorme o id, número ou nome do Banco a ser excluirdo: ");
                    pesquisaBanco = entrada.nextLine();
                    //chama o método que pesquisa o banco
                    temp = pesquisaBanco(pesquisaBanco);
                    if (temp == null) {//banco n[ao encontrado
                        System.out.println("Banco nao foi encontrado");
                    } else {
                        banco.remove(temp);
                        System.out.println("\nBanco excluido com sucesso");
                    }
                    break;
                case 5://vamos atualizar um banco
                    System.out.println("\nInforme o id, numero ou nome do Banco a ser atualizado: ");
                    pesquisaBanco = entrada.nextLine();
                    //chama metodo banco
                    temp = pesquisarBanco(pesquisaBanco);
                    if (temp == null) {
                        System.out.println("\nO banco nao foi encontrado.");
                    } else {
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNúmero: " + temp.getNumero());
                        System.out.println("\nNome: " + temp.getNome());
                        System.out.println("\nQuant Agência: " + temp.getAgencias());

                        System.out.println("\nInforme os novos dados:");
                        System.out.println("\nNovo numero");
                        String novoNumeroBanco = entrada.nextLine();
                        System.out.println("\nNovo nome id Banco: ");
                        String novoNomeBanco = entrada.nextLine();
                        //vamos atualizar os dados deste banco no ArrayList
                        temp.setNome(novoNomeBanco);
                        temp.setNumero(novoNumeroBanco);
                        System.out.println("\nBanco atualizar com sucesso.");
                    }
                    break;
                case 6:
                    return 0;//volta para menu principal
            }
        }
    }

    //método pesquisar um banco pelo id, numero ou nome e retorna um objeto da classe Banco
    public Banco pesquisaBanco(String pesquisaBanco) {
        Banco b = null;

        //este banco existe?
        for (int i = 0; i < banco.size(); i++) {
            //pesquisa pelo id
            if (Integer.toString(banco.get(i).getId()).equals(pesquisaBanco)) {
                return banco.get(i);
            }//pesquisar por nome
            else if (banco.get(i).getNome().contains(pesquisaBanco)) {
                return banco.get(i);
            }//pesquisa pelo número
            else if (banco.get(i).getNome().contains(pesquisaBanco)) {
                return banco.get(i);
            }
        }
        return b;
    }

    //menu para cadastrar, lista, pesquisar, excluir e atualizar as pessoas (futuros clientes
    public int menuGerenciarPessoas() {
        Pessoa temp;//serve para várias operações neste menu
        String pesquisaPessoa;//serve para as pesquisas das pessoas
        while (true)//mostra o menu de forma repetitiva até o ussuário usar a opção de sair
        {
            System.out.println("\n::G E R E N C I A R  P E S S O A S::");
            System.out.println("Escolha a opção desejada");
            System.out.println("1 - Nova pessoa(Futuro Cliente");
            System.out.println("2 - Lista Pessoa");
            System.out.println("3 - Pesquisa Pessoa");
            System.out.println("4 - Excluir Pessoa");
            System.out.println("5 - Atualizar Pessoa");
            System.out.println("6 - Voltar Menu Anterior");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("\nNome: ");
                    String nomePessoa = entrada.nextLine();
                    System.out.println("\nIdade: ");
                    int idade = Integer.parseInt(entrada.nextLine());
                    System.out.println("\nSexo: ");
                    char sexo = entrada.nextLine().charAt(0);

                    //vamos incrementar o contador de pessoas
                    Pessoa.contadorPessoas++;
                    //agora vamos criar um objeto da classe Pessoa
                    Pessoa p = new Pessoa(Pessoa.contadorPessoas, nomePessoa, idade, sexo);
                    //e finalmente mostrams uma mensagem de sucesso.
                    System.out.println("\nA pessoa foi criada com sucesso.");
                    break;

                case 2://vamos lista as pessoas cadastradas
                    if (pessoa.isEmpty()) {
                        System.out.println("\nNão há nenhuma pessoa cadastrada.");
                    } else {
                        for (int i = 0; i < pessoa.size(); i++) {
                            temp = pessoa.get(i);//obtém a pessoa da interação atual
                            System.out.println("\nId: " + temp.getId());
                            System.out.println("\nNome: " + temp.getNome());
                            System.out.println("\nIdade: " + temp.getIdade());
                            System.out.println("\nSexo: " + temp.getSexo());
                            System.out.println("Quantidade de Contas Bancárias :" + temp.getContas(banco).size());

                        }
                    }
                    break;
                case 3://vamos pesquisar uma pessoa
                    System.out.println("\nInforme o id ou nome da pessoa: ");
                    pesquisaPessoa = entrada.nextLine();
                    //chamamos o método que pesquisa a pessoa
                    temp = pesquisarPessoa(pesquisaPessoa);
                    if (temp == null) {//pessoa não encontrada
                        System.out.println("\nA pessoa não encontrada.");
                    } else//mostra e a pessoa nao encontrada
                    {
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNome: " + temp.getNome());
                        System.out.println("\nIdade: " + temp.getIdade());
                        System.out.println("\nSexo: " + temp.getSexo());
                        System.out.println("Quantidade de Contas Bancárias :" + temp.getContas(banco).size());
                    }
                    break;
                case 4://vamos excluir uma pessoa
                    System.out.println("\nInforme o id ou nome da pessoa a ser Excluida: ");
                    pesquisaPessoa = entrada.nextLine();
                    //chamamos o método que pesquisa a pessoa
                    temp = pesquisarPessoa(pesquisaPessoa);
                    if (temp == null) {//pessoa não encontrada
                        System.out.println("\nPessoa não foi encontrada.");
                    } else {//vamos excluir a pessoa. Atenção: Se houve conta bancárias relacionadas a esta pessoa, então a exclusao da conta bancária deverá ser feita primeiro
                        if(temp.getContas(banco).size()>0){
                            System.out.println("\nOps! Esta pessoa possui contas bancárias. Exclua as contas primeiro.");
                        }else{
                            pessoa.remove(temp);
                            System.out.println("\nPessoa excluida com sucesso!  :)");
                        }
                    }
                    break;
                case 5://vamos atualizar uma pessoa
                    System.out.println("\nInforme o id ou nome da pessoa a ser atualizada: ");
                    pesquisaPessoa = entrada.nextLine();
                    //chamamos o método que pesquisa a pessoa
                    temp = pesquisarPessoa(pesquisaPessoa);
                    if(temp == null){//pessoa não encontrada
                        System.out.println("\nA pessoa não foi encontrada");
                    }else{//mostra a pessoa encontrada
                        System.out.println("\nId: "+temp.getId());
                            System.out.println("\nNome: "+temp.getNome());
                            System.out.println("\nIdade: "+temp.getIdade());
                            System.out.println("\nSexo: "+temp.getSexo());
                            System.out.println("Quantidade de Contas Bancárias :"+temp.getContas(banco).size());
                            
                            System.out.println("\nInforme os novos dados: ");
                            System.out.println("\nNovo Nome: ");
                            String novoNomePessoa = entrada.nextLine();
                            System.out.println("\nNova idade: ");
                            int novaIdadePessoa = Integer.parseInt(entrada.nextLine());
                            System.out.println("\nNovo sexo: ");
                            char novoSexoPessoa = entrada.nextLine().charAt(0);
                            //vamos atualizar os dadaos dessa pessoa no ArryList :)
                            temp.setNome(novoNomePessoa);
                            temp.setId(novaIdadePessoa);
                            temp.setSexo(novoSexoPessoa);
                    }
                    break;
                case 6:
                    return 0;//volta para menu principal
            }
        }
    }
    //método que pesquisa uma pessoa pela id, número ou nome e retorna um objeto da classe Pessoa
    public Pessoa pesquisarpessoa(String pesquisarPessoa){
        Pessoa p =null;
        //pesquisa pelo id
        for (int i = 0; i < pessoa.size(); i++) {
            //pesquisar pelo id
            if(Integer.toString(pessoa.get(i).getId()).equals(pesquisarPessoa)){
                return pessoa.get(i);
            }else if (pessoa.get(i).getNome().contains(pesquisarPessoa)){//pessoas por nome
                return pessoa.get(i);
            }
        }
        return p;
    }
    //menu para cadastrar,lista, pesquisa, excluir e atualizar as agências
    public int menuGerenciarAgencia(){
        Agencia temp; //serve para várias operações neste menu
        String pesquisarAgencia;//seve para as pesquisas das agências
        Banco bancoAtual = null;//guarda o banco atual
        //para gerenciar uma agência nós precisamos de um banco
        while(bancoAtual){
            System.out.println("\nInforme o id, número ou nome do banco: ");
           String pesquisaBanco = entrada.nextLine();
            //chamamos o método que pesquisa o banco
            Banco b = pesquisaBanco(pesquisaBanco);
            if(b == null){//banco não encontrado
                System.out.println("\nO banco não foi encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior:");
                int opcao = Integer.parseInt(entrada.nextLine());
                if(opcao == 2){
                    return 1 ;//saímos daqui e voltamos para menu anterior
                }
            }else{//banco encontrado. Vamos proseguir com as agências
                bancoAtual = b;
            }
        }
        //Atenção: o menu abaixo deverá se exibido somente se um banco for selecionado
        while(true){//mostra o menu de forma repetitiva até o usuário usar o opção de sair
            System.out.println("\n:: G E R E N C I A R  A G Ê N C I A ::\n");
            System.out.println("Escolha a opção desejada");
            System.out.println("1 - Nova Agência");
            System.out.println("2 - Listar Agências");
            System.out.println("3 - Pesquisar Agências");
            System.out.println("4 - Excluir Agência");
            System.out.println("5 - Atualizar Agência");
            System.out.println("6 - Voltar ao menu anterior");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());//lê a opção do usuario
            
            switch(opcao){
                case 1: //vamos cadastrar uma nova agência
                    System.out.println("\nNúmero da Agência:");
                    String numeroAgencia = entrada.nextLine();
                    System.out.println("\nCidade/Estado: ");
                    String cidadeAgencia = entrada.nextLine();
                    
                    //vamos incrementar o contador de agências
                    Agencia.contadorAgencias++;
                    //e finalmente mostra uma mensagem de sucesso.
                    System.out.println("\nA Agência foi criada comsucesso.");
                    break;
                case 2://vamos listar agência cadastrada no banco selencuindado
                    if(bancoAtual.getAgencias().isEmpty()){//isempty = vazio nao a lista cadastrada
                        System.out.println("\nNão há nenhuma agência cadastrada neste banco.");
                    }else{
                        for (int i = 0; i < bancoAtual.getAgencias().size(); i++) {
                        temp = bancoAtual.getAgencias().get(i);//obtém a agência da interação atual
                            System.out.println("\nId: "+temp.getId());
                            System.out.println("\nNúmero: "+temp.getNumero());
                            System.out.println("\nCidade/Estado: "+temp.getCidade());
                            System.out.println("\nQuantas Contas Bancárias:"+temp.getContas().size());
                            
                        }
                    }
                    break;
                case 3://vamos pesquisar uma agência
                    System.out.println("\nInforme o id, número ou cidade da agência: ");
                    pesquisaAgencia = entrada.nextLine():
                    //chama o método que pesquisa a agência
                    temp = pesquisarAgencia(BancoAtual, pesquisaAgencia);
                    if(temp == null){//agencia nao encontrada
                        System.out.println("\nA agência não foi encontrada");
                    }else{//mostra agencia encontrada
                        System.out.println("\nId: "+temp.getId());
                            System.out.println("\nNúmero: "+temp.getNumero());
                            System.out.println("\nCidade/Estado: "+temp.getCidade());
                            System.out.println("\nQuantas Contas Bancárias:"+temp.getContas().size()); 
                    }
                    break;
                case 4://vamos excluir uma agência
                    System.out.println("\nInforme o id, número ou cidade da agência");
                    pesquisaAgencia=entrada.nextLine();
                    //chama o método que pesquisa a agência
                    temp = pesquisarAgencia(BancoAtual, pesquisaAgencia);
                    if(temp == null){//Agência não encontrada
                        System.out.println("\nAgência não foi encontrada");
                    }else{//vamos excluir esta agência.Atenção. Ao excluir uma agência, todas suas contas seão excluidas támbem.
                        bancoAtual.getAgencias().remove(temp);
                        System.out.println("\nAgência excluida com sucesso.");
                    }
                    break;
                    
                case 5://vamos atualizar uma agência
                    System.out.println("\nInforme o id, número ou cidade da agência: ");
                    pesquisarAgencia = entrada.nextLine();
                    //chamamos o método que pesquisa a agência
            }
        }
    }
}
