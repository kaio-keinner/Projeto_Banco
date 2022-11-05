/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author teclab
 */
public class Sistema {

    private ArrayList<Pessoa> pessoas = new ArrayList<>();//Arrey de pessoas
    private ArrayList<Banco> bancos = new ArrayList<>();//ArrayList de bancos
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
                    menuGerenciarAgencia();
                    break;
                case 4: // vamos gerenciar as contas de uma determinada agência de um determinado banco
                    menuGerenciarContas();
                    break;
                case 5:
                    return 1; // voltar para o menu Principal
                default://caso não escolha nada cai no default 
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
                    System.out.println("\nNome do banco:");
                    String nomeBanco = entrada.nextLine();
                    //agora vamos criar um novo objeto da classe Banco
                    Banco b = new Banco(Banco.contadorBancos, nomeBanco, numeroBanco);
                    // e adicionamos no ArrayListe de bancos
                    bancos.add(b);
                    // e finalmemnte mostramos uma mensagem de sucesso.
                    System.out.println("\nO Banco foi criado com sucesso!!!");
                    break;
                case 2://vamos listar os bancos cadastrados
                    if (bancos.isEmpty()) {
                        System.out.println("\nNão há nenhum banco cadastrado.");
                    } else {
                        for (int i = 0; i < bancos.size(); i++) {//size pega todo o tamanho do lista
                            temp = bancos.get(i);//obtém o banco da iteração atual
                            System.out.println("\nId: " + temp.getId());
                            System.out.println("\nNúmero: " + temp.getNumero());
                            System.out.println("\nNome: " + temp.getNome());
                            System.out.println("\nQuantas agéncias: " + temp.getAgencias().size());
                        }
                    }
                    break;
                case 3:// vamos pesquiar um banco
                    System.out.println("\nInforme o id, número ou nome do Banco");
                    pesquisaBanco = entrada.nextLine();
                    //chama o método que pesquisa o banco
                    temp = pesquisarBanco(pesquisaBanco);
                    if (temp == null) {//banco não encontrado
                        System.out.println("\nO banco não foi encontrado.:/");
                    } else {
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNúmero: " + temp.getNumero());
                        System.out.println("\nNome: " + temp.getNome());
                        System.out.println("\nQuant Agência: " + temp.getAgencias().size());
                    }
                    break;
                case 4://vamos excluir um banco
                    System.out.println("\nIndorme o id, número ou nome do Banco a ser excluirdo: ");
                    pesquisaBanco = entrada.nextLine();
                    //chama o método que pesquisa o banco
                    temp = pesquisarBanco(pesquisaBanco);
                    if (temp == null) {//banco n[ao encontrado
                        System.out.println("Banco nao foi encontrado");
                    } else {
                        bancos.remove(temp);
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
    public Banco pesquisarBanco(String pesquisaBanco) {
        Banco b = null;

        //este banco existe?
        for (int i = 0; i < bancos.size(); i++) {
            //pesquisa pelo id
            if (Integer.toString(bancos.get(i).getId()).equals(pesquisaBanco)) {
                return bancos.get(i);
            }//pesquisar por nome
            else if (bancos.get(i).getNome().contains(pesquisaBanco)) {
                return bancos.get(i);
            }//pesquisa pelo número
            else if (bancos.get(i).getNumero().contains(pesquisaBanco)) {
                return bancos.get(i);
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
                    if (pessoas.isEmpty()) {
                        System.out.println("\nNão há nenhuma pessoa cadastrada.");
                    } else {
                        for (int i = 0; i < pessoas.size(); i++) {
                            temp = pessoas.get(i);//obtém a pessoa da interação atual
                            System.out.println("\nId: " + temp.getId());
                            System.out.println("\nNome: " + temp.getNome());
                            System.out.println("\nIdade: " + temp.getIdade());
                            System.out.println("\nSexo: " + temp.getSexo());
                            System.out.println("Quantidade de Contas Bancárias :" + temp.getContas(bancos).size());

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
                        System.out.println("Quantidade de Contas Bancárias :" + temp.getContas(bancos).size());
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
                        if (temp.getContas(bancos).size() > 0) {
                            System.out.println("\nOps! Esta pessoa possui contas bancárias. Exclua as contas primeiro.");
                        } else {
                            pessoas.remove(temp);
                            System.out.println("\nPessoa excluida com sucesso!  :)");
                        }
                    }
                    break;
                case 5://vamos atualizar uma pessoa
                    System.out.println("\nInforme o id ou nome da pessoa a ser atualizada: ");
                    pesquisaPessoa = entrada.nextLine();
                    //chamamos o método que pesquisa a pessoa
                    temp = pesquisarPessoa(pesquisaPessoa);
                    if (temp == null) {//pessoa não encontrada
                        System.out.println("\nA pessoa não foi encontrada");
                    } else {//mostra a pessoa encontrada
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNome: " + temp.getNome());
                        System.out.println("\nIdade: " + temp.getIdade());
                        System.out.println("\nSexo: " + temp.getSexo());
                        System.out.println("Quantidade de Contas Bancárias :" + temp.getContas(bancos).size());

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
    public Pessoa pesquisarPessoa(String pesquisarPessoa) {
        Pessoa p = null;
        //pesquisa pelo id
        for (int i = 0; i < pessoas.size(); i++) {
            //pesquisar pelo id
            if (Integer.toString(pessoas.get(i).getId()).equals(pesquisarPessoa)) {
                return pessoas.get(i);
            } else if (pessoas.get(i).getNome().contains(pesquisarPessoa)) {//pessoas por nome
                return pessoas.get(i);
            }
        }
        return p;
    }

    //menu para cadastrar,lista, pesquisa, excluir e atualizar as agências
    public int menuGerenciarAgencia() {
        Agencia temp; //serve para várias operações neste menu
        String pesquisaAgencia;//seve para as pesquisas das agências
        Banco bancoAtual = null;//guarda o banco atual
        //para gerenciar uma agência nós precisamos de um banco
        while (bancoAtual == null) {
            System.out.println("\nInforme o id, número ou nome do banco: ");
            String pesquisaBanco = entrada.nextLine();
            //chamamos o método que pesquisa o banco
            Banco b = pesquisarBanco(pesquisaBanco);
            if (b == null) {//banco não encontrado
                System.out.println("\nO banco não foi encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior:");
                int opcao = Integer.parseInt(entrada.nextLine());
                if (opcao == 2) {
                    return 1;//saímos daqui e voltamos para menu anterior
                }
            } else {//banco encontrado. Vamos proseguir com as agências
                bancoAtual = b;
            }
        }
        //Atenção: o menu abaixo deverá se exibido somente se um banco for selecionado
        while (true) {//mostra o menu de forma repetitiva até o usuário usar o opção de sair
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

            switch (opcao) {
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
                    if (bancoAtual.getAgencias().isEmpty()) {//isempty = vazio nao a lista cadastrada
                        System.out.println("\nNão há nenhuma agência cadastrada neste banco.");
                    } else {
                        for (int i = 0; i < bancoAtual.getAgencias().size(); i++) {
                            temp = bancoAtual.getAgencias().get(i);//obtém a agência da interação atual
                            System.out.println("\nId: " + temp.getId());
                            System.out.println("\nNúmero: " + temp.getNumero());
                            System.out.println("\nCidade/Estado: " + temp.getCidade());
                            System.out.println("\nQuantas Contas Bancárias:" + temp.getContas().size());

                        }
                    }
                    break;
                case 3://vamos pesquisar uma agência
                    System.out.println("\nInforme o id, número ou cidade da agência: ");
                    pesquisaAgencia = entrada.nextLine();
                    //chama o método que pesquisa a agência
                    temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
                    if (temp == null) {//agencia nao encontrada
                        System.out.println("\nA agência não foi encontrada");
                    } else {//mostra agencia encontrada
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNúmero: " + temp.getNumero());
                        System.out.println("\nCidade/Estado: " + temp.getCidade());
                        System.out.println("\nQuantas Contas Bancárias:" + temp.getContas().size());
                    }
                    break;
                case 4://vamos excluir uma agência
                    System.out.println("\nInforme o id, número ou cidade da agência");
                    pesquisaAgencia = entrada.nextLine();
                    //chama o método que pesquisa a agência
                    temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
                    if (temp == null) {//Agência não encontrada
                        System.out.println("\nAgência não foi encontrada");
                    } else {//vamos excluir esta agência.Atenção. Ao excluir uma agência, todas suas contas seão excluidas támbem.
                        bancoAtual.getAgencias().remove(temp);
                        System.out.println("\nAgência excluida com sucesso.");
                    }
                    break;

                case 5://vamos atualizar uma agência
                    System.out.println("\nInforme o id, número ou cidade da agência: ");
                    pesquisaAgencia = entrada.nextLine();
                //chamamos o método que pesquisa a agência
                case 6:
                    return 0;//voltar para menu principal
            }
        }
    }

    //método que pesquisa uma agência pela id, número ou cidade e retorna um objeto da classe agência
    public Agencia pesquisarAgencia(Banco b, String pesquisaAgencia) {
        Agencia a = null;
        //esta agência existe?
        for (int i = 0; i < b.getAgencias().size(); i++) {
            if (Integer.toString(b.getAgencias().get(i).getId()).equals(pesquisaAgencia)) {
                return b.getAgencias().get(i);
                //pesquisar por número da agência
            } else if (b.getAgencias().get(i).getNumero().contains(pesquisaAgencia)) {
                return b.getAgencias().get(i);
            } else if (b.getAgencias().get(i).getCidade().contains(pesquisaAgencia)) {
                return b.getAgencias().get(i);
            }
        }
        return a;
    }

    //menu cadastra,Lista, pesquisar, escluir e atualizar as contas
    public int menuGerenciarContas() {
        Conta temp;//serve para várias operações neste menu
        String pesquisaConta;//serve para as pesquisas das contas
        Banco bancoAtual = null;//guarda o banco atual
        Agencia agenciaAtual = null;
        //para gerenciar uma conta nós precisamos primeiros de um banco
        while (bancoAtual == null) {
            System.out.println("\nInforme o id, número ou nome do banco>");
            String pesquisaBanco = entrada.nextLine();
            //chamamos o método que pesquisa o banco
            Banco b = pesquisarBanco(pesquisaBanco);
            if (b == null) {//banco não encontrado
                System.out.println("\nO banco não foi encontrado.\n\ndigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
                int opcao = Integer.parseInt(entrada.nextLine());
                if (opcao == 2) {
                    return 1;//saímos daqui e voltamos para o menu anterior
                }
            } else {//banco encontrado. Vamos prosseguir com as agênciase
                bancoAtual = b;
            }
        }
        //agora que já temos o banco,vamos selecionar a agência
        while (agenciaAtual == null) {
            System.out.println("\nInforme o id, número ou a cidade da agência: ");
            String pesquisarAgencia = entrada.nextLine();
            //chama o método que pesquisa a agência
            Agencia a = pesquisarAgencia(bancoAtual, pesquisarAgencia);
            if (a == null) {//agência não encontrada
                System.out.println("\nA agência não foi encontrada.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
                int opcao = Integer.parseInt(entrada.nextLine());
                if (opcao == 2) {
                    return 1; //saímos daqui e voltamos para o menu anterior
                }
            } else {//agência encontrada
                agenciaAtual = a;
            }
        }
        //atençaõ:o menu abaixo devera se exibido somente se um banco e uma agencia forems selecionados
        while (true) {//mostra o menu de forma repetitiva até o usuário usar a opcção de sair
            System.out.println("\n:: G E R E N C I A R   C O N T A S ::\n");
            System.out.println("Banco selevionadao: " + bancoAtual.getNome());
            System.out.println("Agência selecionada: " + agenciaAtual.getNumero() + " " + agenciaAtual.getCidade() + "\n");

            System.out.println("Escolha a opção desejada");
            System.out.println("1 - abertura de nova conta");
            System.out.println("2 - Lista Contas");
            System.out.println("3 - Pesquisar Conta");
            System.out.println("4 - Excluir Conta");
            System.out.println("5 - Atualizar Conta");
            System.out.println("6 - Voltar ao Menu Anterior");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());

            //lê a opção do usuário
            switch (opcao) {
                case 1://vamos abrir/cadastrar uma nova conta
                    System.out.println("\nNúmero da Conta: ");
                    String numeroConta = entrada.nextLine();
                    System.out.println("limeite da Conta: ");
                    double limiteConta = Double.parseDouble(entrada.nextLine());
                    //para abrir uma nova conta nós precisamos de um cliente
                    Pessoa cliente = null;//o cliente para o qual uma nova conta será aberta
                    while (cliente == null) {
                        System.out.println("\nInforme id ou nome do cliente");
                        String pesquisaPessoa = entrada.nextLine();
                        cliente = pesquisarPessoa(pesquisaPessoa);
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado\n\nDigte 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
                            int opcaoTemp = Integer.parseInt(entrada.nextLine());
                            if (opcaoTemp == 2) {
                                return 1;//saímos daqui e voltamos para o menu anterior
                            }
                        }
                    }
                    //vamos incrementear o contador de contas
                    Conta.contadorContas++;
                    //agora vamos criar um novo objeto da classe conta
                    Conta c = new Conta(agenciaAtual, cliente, opcao, numeroConta, 0.0, limiteConta);
                    //e o adicionamos no ArrayList de agência selecionada do banco selecionado
                    agenciaAtual.getContas().add(c);
                    //e finalmente mostramos uma mensagem de sucesso.
                    System.out.println("\nUma nova conta foi criada para o cliente: " + cliente.getNome() + "\ncom saldo inicial de R$ 0,00 e limite inicial de R$" + limiteConta);
                    break;
                case 2://vamos listar as contas cadastradas para a agencia e o banco selecionado
                    if (agenciaAtual.getContas().isEmpty()) {//isEmpty= vai pegar a lista e verificar se ela esta vazia
                        System.out.println("\nNão há nenhum conta cadatrada nesta agência");
                    } else {
                        for (int i = 0; i < agenciaAtual.getContas().size(); i++) {
                            temp = agenciaAtual.getContas().get(i);//obtem a conta de iteração
                            System.out.println("\nId da conta bancária:" + temp.getId());
                            System.out.println("\nNúmero da conta bancária: " + temp.getNumero());
                            System.out.println("\nCliente: " + agenciaAtual.getNumero());
                            System.out.println("\nAgência: " + agenciaAtual.getNumero() + " " + agenciaAtual.getCidade());
                            System.out.println("\nBanco: " + bancoAtual.getNumero() + " " + bancoAtual.getNome());
                            System.out.println("Saldo atual: " + temp.getSaldo());
                            System.out.println("Limite atual: " + temp.getLimite());
                        }
                    }
                    break;
                case 3://pesquisa uma conta
                    System.out.println("\nInforme o id, número ou nome do cliente da conta:");
                    pesquisaConta = entrada.nextLine();
                    //chamamos o método que pesquisa a conta
                    temp = pesquisarConta(agenciaAtual, pesquisaConta);
                    if (temp == null) {//conta não encontrada
                        System.out.println("\nA conta não foi encontrada nesta agência");
                    } else {//mostra a conta encontrada
                        System.out.println("\nId da conta bancária: " + temp.getId());
                        System.out.println("\nNúmero da conta: " + temp.getNumero());
                        System.out.println("\nCliente: " + temp.getCliente());
                        System.out.println("\nAgência: " + agenciaAtual.getNumero() + " " + agenciaAtual.getCidade());
                        System.out.println("\nBanco :" + bancoAtual.getNumero() + " " + bancoAtual.getNome());
                        System.out.println("\nSaldo atual: " + temp.getSaldo());
                        System.out.println("\nLimite  atual" + temp.getLimite());
                    }
                    break;
                case 4:
                    System.out.println("\nInforme o id, número ou nome do cliente da conta");
                    pesquisaConta = entrada.nextLine();
                    //chamamos o método que pesquisa a conta
                    temp = pesquisarConta(agenciaAtual, pesquisaConta);
                    if (temp == null) {//conta não encontrada
                        System.out.println("\nA conta não foi encontrada nesta agência");
                    } else {//vamos excluir a conta desta agência
                        agenciaAtual.getContas().remove(temp);
                        System.out.println("\nConta excluida com sucesso.");
                    }
                    break;
                case 5://vamos atualizar uma conta
                    System.out.println("\nInforme o id, número do cliente da conta:");
                    pesquisaConta = entrada.nextLine();
                    //chamamos o método que pesquisa a conta
                    temp = pesquisarConta(agenciaAtual, pesquisaConta);
                    if (temp == null) {//conta não encontrada
                        System.out.println("\nA conta não foi encontrada");
                    } else {
                        //mostra a conta encontrada
                        System.out.println("\nDados atuais da conta agência:");
                        System.out.println("\nId: " + temp.getId());
                        System.out.println("\nNúmero da conta: " + temp.getNumero());
                        System.out.println("\nCliente: " + temp.getCliente().getNome());
                        System.out.println("\nAgência: " + agenciaAtual.getNumero() + "-" + agenciaAtual.getCidade());
                        System.out.println("\nBanco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
                        System.out.println("Saldo atual: " + temp.getSaldo());
                        System.out.println("Limite atual: " + temp.getLimite());
                        System.out.println("\n Informe os novos dados da conta:");
                        System.out.println("\nNovo Número da Conta: ");
                        String novoNumeroConta = entrada.nextLine();
                        System.out.println("\nNovo limite da Conta: ");
                        double novoLimiteConta = Double.parseDouble(entrada.nextLine());

                        //vamos atualizar os dados desta conta no Arraylist da agência atual
                        temp.setNumero(novoNumeroConta);
                        temp.setLimite(novoLimiteConta);
                        System.out.println("\nConta atualizada com sucesso.");
                    }
                    break;
                case 6:
                    return 0;//volta para o menu principal
            }
        }
    }

    //método que pesquisa uma conta pelo id, número ou cliente e retorna umobjeto da classe conta
    public Conta pesquisarConta(Agencia agencia, String pesquisaConta) {
        Conta c = null;

        //esta conta existe?
        for (int i = 1; i < agencia.getContas().size(); i++) {
            //pesquisa a conta pelo id
            if (Integer.toString(agencia.getContas().get(i).getId()).equals(pesquisaConta)) {
                return agencia.getContas().get(i);
            } //pesquisar por Número da conta
            else if (agencia.getContas().get(i).getNumero().contains(pesquisaConta)) {
                return agencia.getContas().get(i);
            } //pesquisa pelo nome do cliente
            else if (agencia.getContas().get(i).getCliente().getNome().contains(pesquisaConta)) {
                return agencia.getContas().get(i);
            }
        }
        return c;
        }
        // método que pesquisa uma conta somente pelo id
    public Conta pesquisarContaPorId(int id) {
        Conta c = null;

// A pesquisa por id tem que percorrer todo o sistema bancário
        for (int i = 0; i < bancos.size(); i++) {
// agora percorremos as agências de cada banco
            Banco banco = bancos.get(i); // representa o banco da iteração atual
            for (int j = 0; j < banco.getAgencias().size(); j++) {
                Agencia agencia = banco.getAgencias().get(j); // agência da iteração atual
                for (int x = 0; x < agencia.getContas().size(); x++) {
                    Conta conta = agencia.getContas().get(x); // conta da iteração atual
                    if (conta.getId() == id) {
                        return conta;
                    }
                }
            }
        }

        return c;
    }

    //menu que permite o acesso do cliente, mediante informação de seus dados
    public int menuCliente() {
        int idConta;//vai servir para as operações nas contas
        Conta conta;//paa operação nas contas ta,bém
        Transacao transacao; //para gerar o extraro das contas
        NumberFormat nf = NumberFormat.getCurrencyInstance();//oara formatar a moeda
        Format formato = new SimpleDateFormat("dd/MM/yyyy");//para formata a data

        //temos que autenticar o cliente no sistema
        Pessoa cliente = null;
        while (cliente == null) {
            System.out.println("\nPrezado(a) cliente, informe o seu id ou nome: ");
            String pesquisaPessoa = entrada.nextLine();
            //chamamos o método que pesquisa a pessoa 
            cliente = pesquisarPessoa(pesquisaPessoa);
            if (cliente == null) {//pessoa não encontrada
                System.out.println("\nCliente não encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
                int opcaoTemp = Integer.parseInt(entrada.nextLine());
                if (opcaoTemp == 2) {
                    return 1;//saimos daqui e voltamos para o menu anterior
                }
            }
        }
        while (true) {//existe continuamente o menu de opções
            System.out.println("\n:: A C E S S O  D O  C L I E N T E ::");
            System.out.println("Cliente Selecionado: " + cliente.getNome() + "\n");
            System.out.println("Escolha a opção desejada");
            System.out.println("1 - Lista Minhas Contas Bancárias");
            System.out.println("2 - Efetuar Depósitos");
            System.out.println("3 - Efetuar Saque");
            System.out.println("4 - Efetuar Transferência");
            System.out.println("5 - Obter Extrato");
            System.out.println("6 - Voltar ao Menu Anterior");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());//Lê a opção do usuário
                    
            switch (opcao) {
                case 1://vamos listar as contas bancárias
                    //percorremos o ArryList de contas do cliente atual
                    for (int i = 0; i < cliente.getContas(bancos).size(); i++) {
                        Conta contaAtualTemp = cliente.getContas(bancos).get(i);//conta da iteração atual
                        System.out.println("\nId: "+contaAtualTemp.getId());
                        System.out.println("\nNúmero da Conta: "+contaAtualTemp.getNumero());
                        System.out.println("\nCliente: "+contaAtualTemp.getCliente().getNome());
                        System.out.println("\nAgência: "+contaAtualTemp.getAgencia().getNumero()+ " - "+contaAtualTemp.getAgencia().getCidade());
                        System.out.println("Saldo Atual: "+contaAtualTemp.getSaldo());
                        System.out.println("Limite Atual: "+contaAtualTemp.getLimite());
                    }
                    break;
                case 2://vamos efetuar um novo depósito em uma das contas
                    System.out.println("\nInforme o Id da Conta:");
                    idConta = Integer.parseInt(entrada.nextLine());
                    System.out.println("(Infotme o valor do deposito)");
                    double valorDeposito = Double.parseDouble(entrada.nextLine());
                    
                    //primeiro temos que encontra eesa conta
                    conta = pesquisarContaPorId(idConta);
                    if(conta == null){//conta não encontrada
                        System.out.println("\nConta não encontrada. Não foi possivel efetuar o depósito");
                    }else 
                        conta.setSaldo(conta.getSaldo()+valorDeposito);
                    //vamos gerar uma transação para o exetrato
                    Transacao.contadorTransacoes++;
                    transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(),"DEPÓSITO",valorDeposito, 'C');
                    conta.getTransações().add(transacao);
                    System.out.println("\nDepósito efetuado com sucesso.\n");
                    break;
                case 3://vamos ofetuar um novo saque em uma das contas
                    System.out.println("\nInforme o Id da Conta: ");
                    idConta = Integer.parseInt(entrada.nextLine());
                    System.out.println("Valor do Saque: ");
                    double valorSaque = Double.parseDouble(entrada.nextLine());
                    
                    //primeiro temos que encontrar essa conta
                    
                    conta = pesquisarContaPorId(idConta);
                    if(conta == null){//conta não encontrada
                        System.out.println("\nConta não encontrada. Não foi possivel efetutar o saque.\n");
                    }else{
                        conta.setSaldo(conta.getSaldo() - valorSaque);
                        //vamos gerar uma trasação para o extrato
                        Transacao.contadorTransacoes++;
                        transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date (),"SAQUE",valorSaque,'D');
                        conta.getTransações().add(transacao);
                        System.out.println("\nSaque realizado com sucesso.\n");
                    }
                    break;
                case 4://vamos efetuar uma nova transferência entre conta
                    System.out.println("\nInforme o Id da Conta do Débito:");
                    idConta = Integer.parseInt(entrada.nextLine());
                    System.out.println("\nInforme o Id da Conta do Crédito:");
                    int ContaCredito = Integer.parseInt(entrada.nextLine());
                    System.out.println("\nValor da Transferência:");
                    double valorTransferencia = Double.parseDouble(entrada.nextLine());
                    
                    //primeiro temos que encontrar a conta do débito, de onde saira o dinheiro
                    conta = pesquisarContaPorId(idConta);
                    //agora temos que encontar a conta do crédito, para onde irá o dinheito
                    Conta contaCredito = pesquisarContaPorId(idContaCredito);
                    
                    if(conta == null){//conta de débito não encontrada
                        System.out.println("\nConta de débito não foi encontrada. Não foi possivel efetuar a transferencia.\n");
                    }else if(contaCredito == null){//conta de crédito não encontrada
                        System.out.println("\nConta de crédito não foi encontrada. Não foi possivel efetuar a transferencia.\n");
                    }else if(contaCredito.getId()==conta.getId()){//transferencia para a mesma conta?
                        System.out.println("\nAs contas de débito e crédito não podem ser a mesma\n");
                    }else{//a conta de débito possui saldo ou limite suficiente?
                        if(valorTransferencia > (conta.getSaldo())+conta.getLimite()){
                            System.out.println("\nSaldo e limite insuficientes para esta transfer~encia.\n");
                        }else{//tirando dinheiro da conta débito
                            conta.setSaldo(conta.getSaldo()- valorTransferencia);
                            //e adicionarmos na conta de crédito
                            contaCredito.setSaldo(contaCredito.setSaldo()+valorTransferencia);
                            //vamos gerar uma transação para o extraro - débito transferencia
                            Transacao.contadorTransacoes++;
                            transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(),"TRANDFERÊNCIA",valorTransferencia,'D');
                            conta.getTransações().add(transacao);
                            //vamos gera uma transação para oo extrato - crédito transferencia
                            Transacao.contadorTransacoes++;
                            transacao = new Transacao (contaCredito, Transacao.contadorTransacoes, new Date(),"TRANSFERÊNCIA",valorTransferencia,'C');
                            contaCredito.getTransacoes().add(transacao);
                            System.out.println("\nTransferência efetuar com sucesso.\n");
                        }
                    }
                    break;
                case 5://vamos listar as contas bancárias
                    break;
                case 6://vamos listar as contas bancárias
                    break;
            }
        }
    }
