/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.MCadastroPessoa.dao;

/**
 *
 * @author USUARIO
 */
public class DAOFactory {
    private static PessoaDAO pessoaDAO = new PessoaDAO();
    private static CarroDAO carroDAO = new CarroDAO();
    
    public static PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    } 
    
    public static CarroDAO getCarroDAO() {
        return carroDAO;
    }
}
