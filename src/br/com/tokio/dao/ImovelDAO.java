package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Imovel;

public class ImovelDAO {

    private Connection connection;

    public ImovelDAO(Connection connection) {
        this.connection = connection;
    }

    // insert
    public void insert(Imovel imovel) {
        String sql = "insert into T_IMOVEL (cep, numero, logradouro, bairro, cidade, pais, estado"
        		+ "vl_area, vl_imovel, cd_cliente) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, imovel.getCep());
            stmt.setInt(2, imovel.getNumero());
            stmt.setString(3, imovel.getLogradouro());
            stmt.setString(4, imovel.getBairro());
            stmt.setString(5, imovel.getCidade());
            stmt.setString(6, imovel.getPais());
            stmt.setString(7, imovel.getEstado());
            stmt.setDouble(8, imovel.getArea());
            stmt.setDouble(9, imovel.getValorImovel());
            stmt.setInt(10, imovel.getIdCliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete
    public void delete(int idImovel, int idCliente) {
        String sql = "delete from T_IMOVEL where cd_imovel = ? and cd_cliente = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idImovel);
            stmt.setInt(2, idCliente);

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update
    public void update(Imovel imovel) {
        String sql = "update T_IMOVEL set cep = ?, numero = ?, logradouro = ?, bairro = ?, "
        		+ "cidade = ?, pais = ?, estado=?,vl_area = ?, vl_imovel = ? where cd_imovel = ? and cd_cliente = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, imovel.getCep());
            stmt.setInt(2, imovel.getNumero());
            stmt.setString(3, imovel.getLogradouro());
            stmt.setString(4, imovel.getBairro());
            stmt.setString(5, imovel.getCidade());
            stmt.setString(6, imovel.getPais());
            stmt.setString(7, imovel.getEstado());
            stmt.setDouble(8, imovel.getArea());
            stmt.setDouble(9, imovel.getValorImovel());
            stmt.setInt(10, imovel.getIdImovel());
            stmt.setInt(11, imovel.getIdCliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // select all
    public List<Imovel> selectAll() {
        String sql = "select * from T_IMOVEL";
        
        List<Imovel> listImoveis = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Imovel imovel = new Imovel();

                imovel.setIdImovel(rs.getInt("cd_imovel"));
                imovel.setCep(rs.getString("cep"));
                imovel.setNumero(rs.getInt("numero"));
                imovel.setLogradouro(rs.getString("logradouro"));
                imovel.setBairro(rs.getString("bairro"));
                imovel.setCidade(rs.getString("cidade"));
                imovel.setPais(rs.getString("pais"));
                imovel.setArea(rs.getDouble("vl_area"));
                imovel.setValorImovel(rs.getDouble("vl_imovel"));
                imovel.setIdCliente(rs.getInt("cd_cliente"));

                listImoveis.add(imovel);
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listImoveis;
    }

    // select by id
    public Imovel selectById(int idImovel, int idCliente) {
        String sql = "select * from T_IMOVEL where cd_imovel = ? and cd_cliente = ?";
        Imovel imovel = new Imovel();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idImovel);
            stmt.setInt(2, idCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                imovel.setIdImovel(rs.getInt("cd_imovel"));
                imovel.setCep(rs.getString("cep"));
                imovel.setNumero(rs.getInt("numero"));
                imovel.setLogradouro(rs.getString("logradouro"));
                imovel.setBairro(rs.getString("bairro"));
                imovel.setCidade(rs.getString("cidade"));
                imovel.setPais(rs.getString("pais"));
                imovel.setArea(rs.getDouble("vl_area"));
                imovel.setValorImovel(rs.getDouble("vl_imovel"));
                imovel.setIdCliente(rs.getInt("cd_cliente"));
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imovel;
    }
}

