/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author LeoFuking
 */
public class DataManager {
    public static void CadastrarCliente(String[] valores){

        String sql = "INSERT INTO cliente (nome,telefone,cpf) VALUES(?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, valores[0]);
            pstm.setString(2, valores[1]);
            pstm.setString(3, valores[2]);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarFornecedor(String[] valores){

        String sql = "INSERT INTO fornecedor (nome,telefone,cnpj,email) VALUES(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, valores[0]);
            pstm.setString(2, valores[1]);
            pstm.setString(3, valores[2]);
            pstm.setString(4, valores[3]);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarFor_prod(String[] valores){

        String sql = "INSERT INTO for_prod (produto_id,fornecedor_id) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.valueOf(valores[0]));
            pstm.setInt(2, Integer.valueOf(valores[1]));
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarItemVenda(String[] valores){

        String sql = "INSERT INTO itenvenda (quantidade,venda_id,produto_id,valor) VALUES(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.valueOf(valores[0]));
            pstm.setInt(2, Integer.valueOf(valores[1]));
            pstm.setInt(3, Integer.valueOf(valores[2]));
            pstm.setFloat(4, Float.valueOf(valores[3]));
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
        
    public static void CadastrarMarca(String valores){

        String sql = "INSERT INTO marca (nome) VALUES(?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, valores);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarModelo(String[] valores){

        String sql = "INSERT INTO modelo (nome,marca_id) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, valores[0]);
            pstm.setInt(2, Integer.valueOf(valores[1]));
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarProduto(String[] valores){

        String sql = "INSERT INTO produto (marca_id,modelo_id,cor_id,pintura_id,valor,quantidade,codigo) VALUES(?,?,?,?,?,?,?)";
        String codigo = valores[0];
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.valueOf(valores[0]));
            pstm.setInt(2, Integer.valueOf(valores[1]));
            pstm.setInt(3, Integer.valueOf(valores[2]));
            pstm.setInt(4, Integer.valueOf(valores[3]));
            pstm.setFloat(5,Float.valueOf(valores[4]));
            pstm.setInt(6, Integer.valueOf(valores[5]));
            pstm.setInt(7,Integer.valueOf(codigo));
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarVenda(String[] valores){

        String sql = "INSERT INTO venda (valor,cliente_id,vendedor_id) VALUES(?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setFloat(1,Float.valueOf(valores[0]));
            pstm.setInt(2, Integer.valueOf(valores[1]));
            pstm.setInt(3, Integer.valueOf(valores[2]));
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void CadastrarFuncionario(String[] valores){
        String sql = "INSERT INTO funcionario (nome,senha,telefone,cpf,permissao) VALUES(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, valores[0]);
            pstm.setString(2, valores[1]);
            pstm.setString(3, valores[2]);
            pstm.setString(4, valores[3]);
            pstm.setInt(5, Integer.valueOf(valores[4]));
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
    
    public static void RemoveById(String tabela, String id){
        String sql = "DELETE FROM " + tabela + " WHERE " + tabela + "_id = " + id;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(pstm != null){
                   pstm.close();
                }
                if(conn != null){
                   conn.close();
                }
            }catch(Exception e){
               e.printStackTrace();
            }
        }
    }
}
