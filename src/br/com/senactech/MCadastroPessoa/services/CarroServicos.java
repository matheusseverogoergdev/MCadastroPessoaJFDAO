/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.MCadastroPessoa.services;

import br.com.senactech.MCadastroPessoa.dao.DAOFactory;
import br.com.senactech.MCadastroPessoa.dao.CarroDAO;
import br.com.senactech.MCadastroPessoa.model.Carro;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class CarroServicos {
    public void cadCarro(Carro cVO) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        cDAO.cadastrarCarro(cVO);
    }
    
    public ArrayList<Carro> getCarros() throws SQLException{
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        return cDAO.buscarCarros();
    }
    
    public boolean verPlaca(String placa) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        return cDAO.verPlaca(placa);
    }
    
    public Carro buscarCarroBD(String placa) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        return cDAO.getByDocBD(placa);
    }
    
    public void deletarCarroBD(int id) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        cDAO.deletarCarro(id);
    }
    
    public void atualizarCarroBD(Carro cVO) throws SQLException {
        CarroDAO cDAO = DAOFactory.getCarroDAO();
        cDAO.atualizarCarro(cVO);
    }    
}
