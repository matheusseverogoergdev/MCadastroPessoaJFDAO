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
import br.com.senactech.MCadastroPessoa.model.Pessoa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class PessoaDAO {
    public void cadastrarPessoa(Pessoa pVO) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        
        // Cria um objeto stat responsável por enviar os comandos sql do Java
        // Para serem executados dentro do BD.
        Statement stat = con.createStatement();
        try {
            //sql vai receber o comando sql
            String sql;
            sql = "INSERT INTO pessoa VALUES (null, '" + pVO.getNomePessoa() + "','"
                    + pVO.getCpf() + "','" + pVO.getEndereco() + "','"
                    + pVO.getTelefone() + "'," + pVO.getIdade() + ","
                    + pVO.isStatus() + ")";
            // Vamos executar o comando construido no string sql
            stat.execute(sql);
        } catch(Exception e) {
            throw new SQLException("Erro ao inserir Pessoa!\n" + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }
    
    public ArrayList<Pessoa> buscarPessoas() throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        try {
            String sql;
            sql = "SELECT * FROM pessoa";
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Pessoa> pessoas = new ArrayList<>();
            while(rs.next()) {
                Pessoa p = new Pessoa();
                // Lado java || Lado banco
                p.setIdPessoa(rs.getInt("idPessoa"));
                p.setNomePessoa(rs.getString("nomePessoa"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(rs.getString("endereco"));
                p.setTelefone(rs.getString("telefone"));
                p.setIdade(rs.getInt("idade"));
                p.setStatus(rs.getBoolean("status"));
                pessoas.add(p);
            };
            return pessoas;
        } catch(SQLException e) {
            throw new SQLException("Erro ao buscar pessoas!\n"
            + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }
    
    public boolean verCPF(String cpf) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        boolean verCPF = false;
        
        try {
            String sql;
            sql = "SELECT cpf FROM pessoa WHERE cpf = '" + cpf + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                verCPF = !rs.wasNull();
            }
        } catch (Exception e) {
            throw new SQLException("Pessoa com este CPF não existe!\n"
            + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        
        return verCPF;
    }
    
    public Pessoa getByDocBD(String cpf) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        Pessoa p = new Pessoa();
        
        try {
            String sql;
            sql = "SELECT * FROM pessoa WHERE cpf = '" + cpf + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                p.setIdPessoa(rs.getInt("idPessoa"));
                p.setNomePessoa(rs.getString("nomePessoa"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(rs.getString("endereco"));
                p.setTelefone(rs.getString("telefone"));
                p.setIdade(rs.getInt("idade"));
                p.setStatus(rs.getBoolean("status"));
            }
        } catch(SQLException e) {
            throw new SQLException("Pessoa com este CPF não existe!\n"
            + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        
        return p;
    }
    
    public void deletarPessoa(int id) throws SQLException{
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        
        try {
            String sql;
            sql = "DELETE FROM pessoa WHERE idPessoa = " + id;
            stat.execute(sql);
        } catch(SQLException e) {
            throw new SQLException("Erro ao deletar Pessoa. \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public void atualizarPessoa(Pessoa pVO) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();    
        
        try {
            String sql;
            sql = "UPDATE pessoa SET "
                    + "nomePessoa = '" + pVO.getNomePessoa() + "',"
                    + "endereco = '" + pVO.getEndereco() + "',"
                    + "idade = " + pVO.getIdade() + ","
                    + "telefone = '" + pVO.getTelefone() + "',"
                    + "status = " + pVO.isStatus() + ""
                    + " WHERE idPessoa = " + pVO.getIdPessoa() + "";
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar Pessoa. \n"
                + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }
    
    public String getNomePessoa(int id) throws SQLException {
        String nomePessoa = null;
        
        try {
            for (Pessoa pes: buscarPessoas()) {
                if (pes.getIdPessoa() == id) {
                    nomePessoa = pes.getNomePessoa();
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Pessoa com este id não existe!\n"
            + e.getMessage());
        } finally {
        }
        
        return nomePessoa;
    }
    
    public int pesqIdPes(String cpf) throws SQLException {
        int idPessoa = 0;
        try {
            for (Pessoa pes : buscarPessoas()) {
                if (pes.getCpf().equals(cpf)) {
                    idPessoa = pes.getIdPessoa();
                    System.out.println("idPessoa: " + idPessoa);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Pessoa com este cpf não existe!\n"
            + e.getMessage());
        }
        return idPessoa;
    }
    
    public String pesqCPFPes(int idPessoa) throws SQLException {
        String cpf = null;
        try {
            for (Pessoa pes : buscarPessoas()) {
                if (pes.getIdPessoa() == idPessoa) {
                    cpf = pes.getCpf();
                    break;
                }
            }
        } catch(SQLException ex) {
            throw new SQLException("Pessoa com este ID não existe!\n"
            + ex.getMessage());
        }
        return cpf;
    }
    
}
