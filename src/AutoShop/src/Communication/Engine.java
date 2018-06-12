/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LeoFuking
 */
public class Engine {
        public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
        
    public static Object GetSingleData(String SQL, String Campo){
        Object Return = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(SQL);
            rset = pstm.executeQuery(SQL);
            while(rset.next()){
                Return = rset.getObject(Campo);
            }
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
        return Return;
    }
    
    public static void ExecuteComand(String SQL){
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(SQL);
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
    
    public static Object[][] GetProductData(int marca,int modelo, String cor, int pintura){
        ArrayList<Object> ReturnList = new ArrayList<Object>();
        int control = 0;
        String sql = " SELECT * FROM produto";
        if(cor != "" || pintura != 0 || modelo != 0 || marca != 0) sql += " WHERE ";
        if(marca   != 0){ sql += "(marca_id = " + marca + ")";if(modelo != 0 || pintura != 0 || cor != "")control++;}
        if(control != 0){ sql += " AND "; control--;}
        if(modelo  != 0){ sql += "(modelo_id = " + modelo + ")";if(pintura != 0 || cor != "")control++;}
        if(control != 0){ sql += " AND "; control--;}
        if(pintura != 0){ sql += "(pintura_id = " + pintura + ")";if( cor != "")control++;}
        if(control != 0){ sql += " AND "; control--;}
        if(cor != "") sql += "( cor_id IN (SELECT cor_id FROM cor WHERE nome LIKE '%"+cor+"%'))";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery(sql);
            while (rset.next()) {  
                ReturnList.add(rset.getInt("marca_id"));
                ReturnList.add(rset.getInt("modelo_id"));
                ReturnList.add(rset.getInt("cor_id"));
                ReturnList.add(rset.getInt("pintura_id"));
                ReturnList.add(rset.getInt("quantidade"));
                ReturnList.add(rset.getFloat("valor"));
                ReturnList.add(rset.getInt("produto_id"));
            }
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
        Object[][] Return = new Object[ReturnList.size()/7][7];
        for(int r = 0; r < ReturnList.size()/7; r++){
            for(int c = 0; c < 7; c++){
                Return[r][c] = ReturnList.get((r*7)+c);
            }
        }
        for(int i = 0; i < ReturnList.size()/7; i++){
            Return[i][0] = GetSingleData("SELECT nome FROM marca WHERE marca_id = " + Return[i][0],"nome");
            Return[i][1] = GetSingleData("SELECT nome FROM modelo WHERE modelo_id = " + Return[i][1],"nome");
            Return[i][2] = GetSingleData("SELECT nome FROM cor WHERE cor_id = " + Return[i][2],"nome");
            Return[i][3] = GetSingleData("SELECT nome FROM pintura WHERE pintura_id = " + Return[i][3],"nome");
        }
        return Return;
    }
    
    public static javax.swing.table.DefaultTableModel ProductToTable(int marca, int modelo, String cor, int pintura){
        return new javax.swing.table.DefaultTableModel(
            GetProductData(marca,modelo,cor,pintura),
            new String [] {
                "Marca", "Modelo", "Cor", "Pintura","Quantidade","Valor","Key"
            }
        );
    }
    
    public static String[] GetCBoxData(String tabela, String campo){
            ArrayList<String> ReturnList = new ArrayList<String>();
            ReturnList.add("");
            String sql = "SELECT " + campo + " FROM " + tabela;
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rset = null;
            try {
                conn = ConnectionFactory.createConnectionToMySQL();
                pstm = conn.prepareStatement(sql);
                rset = pstm.executeQuery(sql);
                while (rset.next()) {
                    ReturnList.add(rset.getString(campo));
                }
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
        String[] ReturnArr = new String[ReturnList.size()];
        ReturnArr = ReturnList.toArray(ReturnArr);
        return ReturnArr;
    }
    
     public static javax.swing.DefaultComboBoxModel RowToComboBox(String tabela, String campo){
        return new javax.swing.DefaultComboBoxModel<>(GetCBoxData(tabela,campo));
    }
     
    public static Object[][] GetVendaData(int venda_id){
        String sql = " SELECT * FROM itenvenda WHERE venda_id = " + venda_id;
        ArrayList<Object> ReturnList = new ArrayList<Object>();
        ArrayList<Object> ProductList = new ArrayList<Object>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rset = null;
            try{
                conn = ConnectionFactory.createConnectionToMySQL();
                pstm = conn.prepareStatement(sql);
                rset = pstm.executeQuery(sql);
                while (rset.next()) {
                    ReturnList.add(rset.getObject("quantidade"));
                    ReturnList.add(rset.getObject("valor"));
                    ReturnList.add(rset.getObject("itenvenda_id"));
                    ProductList.add(rset.getObject("produto_id"));
                }
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
        Object[][] Return = new Object[ReturnList.size()/3][7];
        for(int r = 0; r < ReturnList.size()/3; r++){
            for(int c = 0; c < 3; c++){
                Return[r][c] = ReturnList.get((r*3)+c);
            }
        }
        Object[] Product = new Object[ProductList.size()];
        Product = ProductList.toArray();
        for(int i = 0; i < ReturnList.size()/3; i++){
            Return[i][3] = GetSingleData("SELECT nome FROM marca WHERE marca_id = (SELECT marca_id FROM produto WHERE produto_id =" + Product[i]+")","nome");
            Return[i][4] = GetSingleData("SELECT nome FROM modelo WHERE modelo_id = (SELECT modelo_id FROM produto WHERE produto_id =" + Product[i]+")","nome");
            Return[i][5] = GetSingleData("SELECT nome FROM cor WHERE cor_id = (SELECT cor_id FROM produto WHERE produto_id =" + Product[i]+")","nome");
            Return[i][6] = GetSingleData("SELECT nome FROM pintura WHERE pintura_id = (SELECT pintura_id FROM produto WHERE produto_id =" + Product[i]+")","nome");
        }
        return Return;
    }
    
    public static javax.swing.table.DefaultTableModel VendaToTable(int venda_id){
        return new javax.swing.table.DefaultTableModel(
            GetVendaData(venda_id),
            new String [] {
                "Quantidade","Valor","Key","Marca", "Modelo", "Cor", "Pintura"
            }
        );
    }
    
     public static String[] GetMarcaModelo(boolean modFromMar, String compara){
            ArrayList<String> ReturnList = new ArrayList<String>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rset = null;
            if(modFromMar){
                ReturnList.add("");
                String sql = "SELECT nome FROM modelo WHERE marca_id = "+compara;
                try {
                    conn = ConnectionFactory.createConnectionToMySQL();
                    pstm = conn.prepareStatement(sql);
                    rset = pstm.executeQuery(sql);
                    while (rset.next()) {
                        ReturnList.add(rset.getString("nome"));
                    }
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
            String[] ReturnArr = new String[ReturnList.size()];
            ReturnArr = ReturnList.toArray(ReturnArr);
            return ReturnArr;
        }else{
           String sql = "SELECT nome FROM marca WHERE marca_id = (SELECT marca_id FROM modelo WHERE nome = '"+ compara +"')";
                try {
                    conn = ConnectionFactory.createConnectionToMySQL();
                    pstm = conn.prepareStatement(sql);
                    rset = pstm.executeQuery(sql);
                    while (rset.next()) {
                        ReturnList.add(rset.getString("nome"));
                    }
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
            String[] ReturnArr = new String[ReturnList.size()];
            ReturnArr = ReturnList.toArray(ReturnArr);
            return ReturnArr;   
        }
    }
     
    public static javax.swing.DefaultComboBoxModel MarcaModeloCBox(boolean modToMar, String compara){
        return new javax.swing.DefaultComboBoxModel<>(GetMarcaModelo(modToMar, compara));
    }
    
     public static javax.swing.table.DefaultTableModel ColorToTable(String filtro){
        return new javax.swing.table.DefaultTableModel(
            GetCor(filtro),
            new String [] {
                "Nome","Hex"
            }
        );
    }
    
    public static String[][] GetCor(String filtro){
        ArrayList<String> ReturnList = new ArrayList<String>();
        String sql = "SELECT * FROM cor WHERE nome LIKE '%"+filtro+"%'";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery(sql);
            while(rset.next()){
                ReturnList.add(rset.getString("nome"));
                ReturnList.add(rset.getString("hex"));
            }
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
        String[][] Return = new String[ReturnList.size()/2][2];
        for(int r = 0; r < ReturnList.size()/2; r++){
            for(int c = 0; c < 2; c++){
                Return[r][c] = ReturnList.get((r*2)+c);
            }
        }
        return Return;
    }
    
    public static int GetRowSize(String tabela){
        String sql = "SELECT COUNT(*) AS rowcount FROM "+ tabela;
        int Return = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery(sql);
            rset.next();
            Return = rset.getInt("rowcount");
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
        return Return;
    }
     
    public static javax.swing.DefaultComboBoxModel TabelasCBox(){
        return new javax.swing.DefaultComboBoxModel<>(new String[]{
            "","cliente","cor","fornecedor","for_prod","itenvenda",
            "marca","modelo","pintura","produto","venda","funcionario"
        });
    }
    
    public static javax.swing.DefaultComboBoxModel ColumnNamesCBox(String table){
        return new javax.swing.DefaultComboBoxModel<>(GetColumnNamesCBox(table));
    }
    
    public static String[] GetColumnNames(String table){
        String sql = "select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='"+table+"'";
        ArrayList<String> ReturnList = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery(sql);
            while(rset.next()){
                ReturnList.add(rset.getString("COLUMN_NAME"));
            }
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
        String Return[] = new String[ReturnList.size()];
        Return = ReturnList.toArray(Return);
        return Return;
    }
    
    public static String[] GetColumnNamesCBox(String table){
        String sql = "select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='"+table+"'";
        ArrayList<String> ReturnList = new ArrayList<String>();
        ReturnList.add("");
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery(sql);
            while(rset.next()){
                ReturnList.add(rset.getString("COLUMN_NAME"));
            }
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
        String Return[] = new String[ReturnList.size()];
        Return = ReturnList.toArray(Return);
        return Return;
    }
    
    public static Object[][] GetTableRows(String table, String values[]){
        String sql = "SELECT * FROM "+ table;
        ArrayList<Object> ReturnList = new ArrayList<Object>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rset = null;
            try{
                conn = ConnectionFactory.createConnectionToMySQL();
                pstm = conn.prepareStatement(sql);
                rset = pstm.executeQuery(sql);
                while (rset.next()) {
                    for(int i = 0;i < values.length;i++){
                        ReturnList.add(rset.getObject(values[i]));
                    }
                }
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
            int size = values.length;
        Object[][] Return = new Object[ReturnList.size()/size][7];
        for(int r = 0; r < ReturnList.size()/size; r++){
            for(int c = 0; c < size; c++){
                Return[r][c] = ReturnList.get((r*size)+c);
            }
        }
        return Return;
    }
    
    public static javax.swing.table.DefaultTableModel SetTableModel(String table){
            String[] values = GetColumnNames(table);
        return new javax.swing.table.DefaultTableModel( 
            GetTableRows(table,values),values
        );
    }
    
        public static int[] GetPermissao(String values[]){
        String sql = "SELECT * FROM funcionario WHERE nome LIKE '"+values[0]+"' AND senha LIKE '"+values[1]+"'";
        int Return[] = new int[2];
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rset = null;
            try{
                conn = ConnectionFactory.createConnectionToMySQL();
                pstm = conn.prepareStatement(sql);
                rset = pstm.executeQuery(sql);
                while (rset.next()) {
                    Return[0] = rset.getInt("permissao");
                    Return[1] = rset.getInt("funcionario_id");
                }
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
        return Return;
    }
        
        public static Object[][] GetFilterTableRows(String table, String values[], String filter[]){
        String sql = "SELECT * FROM "+ table+" WHERE "+filter[0]+" LIKE '%"+filter[1]+"%'";
        ArrayList<Object> ReturnList = new ArrayList<Object>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rset = null;
            try{
                conn = ConnectionFactory.createConnectionToMySQL();
                pstm = conn.prepareStatement(sql);
                rset = pstm.executeQuery(sql);
                while (rset.next()) {
                    for(int i = 0;i < values.length;i++){
                        ReturnList.add(rset.getObject(values[i]));
                    }
                }
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
            int size = values.length;
        Object[][] Return = new Object[ReturnList.size()/size][7];
        for(int r = 0; r < ReturnList.size()/size; r++){
            for(int c = 0; c < size; c++){
                Return[r][c] = ReturnList.get((r*size)+c);
            }
        }
        return Return;
    }
    
    public static javax.swing.table.DefaultTableModel SetFilterTableModel(String table,String[] filter){
            String[] values = GetColumnNames(table);
        return new javax.swing.table.DefaultTableModel( 
            GetFilterTableRows(table,values,filter),values
        );
    }
}
