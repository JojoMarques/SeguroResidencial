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
    
    /** construtor de ImovelDAO
	 * @param Connection - connection
	 * */
    public ImovelDAO(Connection connection) {
        this.connection = connection;
    }

    /** insere um imovel
	 * @param Imovel - imovel
	 * */
    public void insert(Imovel imovel) {
        String sql = "insert into T_IMOVEL (cep, numero, logradouro, bairro, cidade, pais, estado, vl_area, vl_imovel, cd_cliente) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

    /** deleta um imovel
	 * @param Imovel - imovel
	 * */
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

    /** atualiza um imovel
	 * @param Imovel - imovel
	 * */
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

    /** busca todos os imoveis
	 * @return List<Imovel> - lista de imoveis
	 * */
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
                imovel.setEstado(rs.getString("estado"));
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

    /** busca um imovel pelo id
	 * @param int - id do imovel
	 * @return Imovel - imovel
	 * */
    public Imovel selectById(int idImovel) {
        String sql = "select * from T_IMOVEL where cd_imovel = ?";
        Imovel imovel = new Imovel();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idImovel);
           
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                imovel.setIdImovel(rs.getInt("cd_imovel"));
                imovel.setCep(rs.getString("cep"));
                imovel.setNumero(rs.getInt("numero"));
                imovel.setLogradouro(rs.getString("logradouro"));
                imovel.setBairro(rs.getString("bairro"));
                imovel.setCidade(rs.getString("cidade"));
                imovel.setPais(rs.getString("pais"));
                imovel.setEstado(rs.getString("estado"));
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
    
    /** busca um imovel pelo id do cliente que o tem
	 * @param int - id do cliente
	 * @return Imovel - imovel
	 * */
    public Imovel selectByClienteId(int idCliente) {
        String sql = "select * from T_IMOVEL where cd_cliente = ?";
        Imovel imovel = new Imovel();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                imovel.setIdImovel(rs.getInt("cd_imovel"));
                imovel.setCep(rs.getString("cep"));
                imovel.setNumero(rs.getInt("numero"));
                imovel.setLogradouro(rs.getString("logradouro"));
                imovel.setBairro(rs.getString("bairro"));
                imovel.setCidade(rs.getString("cidade"));
                imovel.setPais(rs.getString("pais"));
                imovel.setEstado(rs.getString("estado"));
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

