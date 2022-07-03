/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senactech.MCadastroPessoa.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.senactech.MCadastroPessoa.conexao.Conexao;
import br.com.senactech.MCadastroPessoa.model.Carro;

/**
 *
 * @author USUARIO
 */
public class CarroDAO {
    public void cadastrarCarro(Carro cVO) throws SQLException {
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "INSERT INTO carro VALUES (null,'" + cVO.getPlaca() + "','"
                    + cVO.getMarca() + "','" + cVO.getModelo() + "',"
                    + cVO.getAnoFabricacao() + "," + cVO.getAnoModelo() + ",'"
                    + cVO.getCor() + "', " + cVO.getnPortas() + ", " + cVO.getIdPessoa() + ")";
            
            System.out.println(sql);
            stat.execute(sql);
        } catch(Exception e) {
            throw new SQLException("Erro ao inserir Carro!\n" + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }
    
    public ArrayList<Carro> buscarCarros() throws SQLException {
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        try {
            String sql;
            sql = "SELECT * FROM carro";
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro c = new Carro();
                c.setIdCarro(rs.getInt("idCarro"));
                c.setIdPessoa(rs.getInt("idPessoa"));
                c.setAnoFabricacao(rs.getInt("anoFabricacao"));
                c.setAnoModelo(rs.getInt("anoModelo"));
                c.setnPortas(rs.getInt("nPortas"));
                c.setPlaca(rs.getString("placa"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setCor(rs.getString("cor"));
                carros.add(c);
            }
            return carros;
        } catch(SQLException e) {
            throw new SQLException("Erro ao buscar os carros!\n"
            + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }
    
    public boolean verPlaca(String placa) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        boolean verPlaca = false;
        
        try {
            String sql;
            sql = "SELECT placa FROM carro WHERE placa = '" + placa + "'";
            ResultSet rs = stat.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                verPlaca = !rs.wasNull();
            }
        } catch (Exception e) {
            throw new SQLException("Carro com esta placa não existe!\n"
            + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        return verPlaca;
    }
    
    public Carro getByDocBD(String placa) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        Carro c = new Carro();
        
        try {
            String sql;
            sql = "SELECT * FROM carro WHERE placa = '" + placa + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                c.setIdCarro(rs.getInt("idCarro"));
                c.setPlaca(rs.getString("placa"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAnoFabricacao(rs.getInt("anoFabricacao"));
                c.setAnoModelo(rs.getInt("anoModelo"));
                c.setCor(rs.getString("cor"));
                c.setnPortas(rs.getInt("nPortas"));
                c.setIdPessoa(rs.getInt("idPessoa"));
            }
        } catch(SQLException e) {
            throw new SQLException("Carro com esta placa não existe!\n"
            + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        
        return c;
    }
    
    public void deletarCarro(int id) throws SQLException{
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "DELETE FROM carro WHERE idCarro = " + id;
            stat.execute(sql);
        } catch(SQLException e) {
            throw new SQLException("Erro ao deletar Carro. \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public void atualizarCarro(Carro cVO) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();    
        
        try {
            String sql;
            sql = "UPDATE carro SET "
                    + "placa = '" + cVO.getPlaca() + "',"
                    + "marca = '" + cVO.getMarca() + "',"
                    + "modelo = '" + cVO.getModelo() + "',"
                    + "anoFabricacao = " + cVO.getAnoFabricacao() + ","
                    + "anoModelo = " + cVO.getAnoModelo() + ","
                    + "cor = '" + cVO.getCor() + "',"
                    + "nPortas = " + cVO.getnPortas() + ","
                    + "idPessoa = " + cVO.getIdPessoa()
                    + " WHERE idCarro = " + cVO.getIdCarro() + "";
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar Carro. \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
}
