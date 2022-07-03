/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.MCadastroPessoa.services;

import br.com.senactech.MCadastroPessoa.dao.DAOFactory;
import br.com.senactech.MCadastroPessoa.dao.PessoaDAO;
import br.com.senactech.MCadastroPessoa.model.Pessoa;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class PessoaServicos {
    public void cadPessoa(Pessoa pVO) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        pDAO.cadastrarPessoa(pVO);
    }
    
    public ArrayList<Pessoa> getPessoas() throws SQLException{
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.buscarPessoas();
    }
    
    public boolean verCPF(String cpf) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.verCPF(cpf);
    }
    
    public Pessoa buscarPessoaBD(String cpf) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.getByDocBD(cpf);
    }
    
    public void deletarPessoaBD(int id) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        pDAO.deletarPessoa(id);
    }
    
    public void atualizarPessoaBD(Pessoa pVO) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        pDAO.atualizarPessoa(pVO);
    }
    
    public String getNomePessoa(int id) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.getNomePessoa(id);
    }
    
    public int getIdPessoa(String cpf) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.pesqIdPes(cpf);
    }
    
    public String getCPFPessoa(int id) throws SQLException {
        PessoaDAO pDAO = DAOFactory.getPessoaDAO();
        return pDAO.pesqCPFPes(id);
    }
}
