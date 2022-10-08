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
    private ArrayList<Pessoa> pessoa = new ArrayList<>();//arrey de pessoas
    private ArrayList<Banco> banco = new ArrayList<>();//ArrayList de bancos
    Scanner entrada = new Scanner(System.in);
    
    public static void main(String[]args){
        //Chama o menu principal 
        Sistema s = new Sistema();
        s.menuPrincipal();
    }
    
    //metodo que exibe o menu principal do sistema;
    public void menuPrincipal(){
        while (true){
            System.out.println("\n:: S I S T E M A  B A N C Á R I O::");
            System.out.println("Bem vindo(a) ao sistema. Escolha a opção desejada");
            System.out.println("1 - Administrar o Sistema");
            System.out.println("2 - Acessar como Cliente");
            System.out.println("3 - Sair");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            
            switch(opcao){
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
    public int menuAdministrarSistema(){
        while (true){ //Exibe continuamente o menu de opções;
            System.out.println("\n:: A D M I N S T R A Ç Ã O  D O  S I T E M A::\n");
            System.out.println("Escola a opção desejada ");
            System.out.println("1 - Gerenciar Bancos");
            System.out.println("2 - Gerenciar Pessoas");
            System.out.println("3 - Gerenciar Agências");
            System.out.println("4 - Gerenciar Contas");
            System.out.println("5 - Voltar Menu Anterior");
            System.out.println("Sua opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao){
                case 1 :// vamos gerenciar banco
                    menuGerenciarBancos();
                    break;
                case 2 : // vamos gerenciar os as pessoas(que serão Futuroos clientes
                    menuGerenciarPessoas();
                    break;
                case 3 : // vamos gerenciar as contas de uma determinada agência de um determinado banco
                    menuGerenciarAgencias();
                    break;
                case 4 : // vamos gerenciar as contas de uma determinada agência de um determinado banco
                    menuGerenciarContas();
                    break;
                case 5 : 
                    return 1 ; // voltar para o menu Principal
                default:
                    throw new AssertionError();
                           
            }
        }
    }
    //menu cadastra, Listar, excluir e atualizar
    public int menuGerenciarBancos(){
     Banco temp; //serve para várias operações neste menu
     String pesquisaBanco; //serve para as pesquisas dos bancos
     while(true){ //mostra o menu de forma repetitiva até o usuário usar a opção de sair
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
             case 1 : //vamos cadastra um novo banco
                 System.out.println("\nNúmero do Banco: ");
                 String numeroBanco = entrada.nextLine();
                 // vamos incrementar o contador de bancos
                 Banco.contadorBancos++;
                 
                 //agora vamos criar um novo objeto da classe Banco
                 Banco b =new Banco(Banco.contadorBancos, nomeBanco, numeroBanco);
                 // e adicionamos no ArrayListe de bancos
                 banco.add(b);
                 // e finalmemnte mostramos uma mensagem de sucesso.
                 System.out.println("\nO Banco foi criado com sucesso!!!");
                 break;
             case 2 ://vamos listar os bancos cadastrados
                 if(banco.isEmpty()){
                     System.out.println("\nNão há nenhum banco cadastrado.");
                 }else{
                     for (int i = 0; i < banco.size(); i++) {
                         temp =banco.get(i);//obtém o banco da iteração atual
                         System.out.println("\nId: "+temp.getId());
                         System.out.println("\nNúmero: "+temp.getNumero());
                         System.out.println("\nNome: "+temp.getNome());
                         System.out.println("\nQuantas agéncias: "+temp.getAgencias());
                     }
                 }
                 break;
             case 3 :// vamos pesquiar um banco
                 System.out.println("\nInforme o id, número ou nome do Banco");
                 pesquisaBanco = entrada.nextLine();
                 //chama o método que pesquisa o banco
                 temp = pesquisaBanco(pesquisaBanco);
                 if(temp == null){//banco não encontrado
                     System.out.println("\nO banco não foi encontrado.:/");
                 }else{
                     System.out.println("\nId: "+temp.getId());
                     System.out.println("\nNúmero: "+temp.getNumero());
                     System.out.println("\nNome: "+temp.getNome());
                     System.out.println("\nQuant Agência: "+temp.getAgencias());
                 }
                 break;
             case 4://vamos excluir um banco
                 System.out.println("\nIndorme o id, número ou nome do Banco a ser excluirdo: ");
                 pesquisaBanco = entrada.nextLine();
                 //chama o método que pesquisa o banco
                 temp = pesquisaBanco(pesquisaBanco);
                 if(temp == null){//banco n[ao encontrado
                     System.out.println("Banco nao foi encontrado");
                 }else{
                     banco.remove(temp);
                     System.out.println("\nBanco excluido com sucesso");
                 }
                 break;
             case 5 ://vamos atualizar um banco
                 System.out.println("\nInforme o id, numero ou nome do Banco a ser atualizado: ");
                 pesquisaBanco = entrada.nextLine();
                 //chama metodo banco
                 temp = pesquisarBanco(pesquisaBanco);
                 if (temp == null){
                     System.out.println("\nO banco nao foi encontrado.");
                 }else{
                      System.out.println("\nId: "+temp.getId());
                     System.out.println("\nNúmero: "+temp.getNumero());
                     System.out.println("\nNome: "+temp.getNome());
                     System.out.println("\nQuant Agência: "+temp.getAgencias());
                     
                     System.out.println("\nInforme os novos dados:");
                     System.out.println("\nNovo numero");
                     String novoNumeroBanco = entrada.nextLine();
                     System.out.println("\nNovo nome id Banco: ");
                     String novoNomeBanco = entrada.nextLine();
                     //vamos atualizar os dados deste banco no ArrayList
                     temp.setNome(novoNomeBanco);
                     temp.setNumerp(novoNumeroBanco);
                     System.out.println("\nBanco atualizar com sucesso.");
                 }
                 break;
             case 6:
                 return 0;//volta para menu principal
         }
     }
    }
}
