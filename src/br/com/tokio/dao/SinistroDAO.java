package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Sinistro;

public class SinistroDAO {

    private Connection connection;

    /** construtor de SinistroDAO
	 * @param Connection - connection
	 * */
    public SinistroDAO(Connection connection) {
        this.connection = connection;
    }

    /** insere um sinistro
	 * @param Sinistro - sinistro
	 * */
    public void insert(Sinistro sinistro) {
    	String sql = "insert into T_SINISTRO (tp_sinistro, dt_sinistro, ds_sinistro, status, cd_seguro, cd_cliente) values (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sinistro.getTipoSinistro());
            stmt.setString(2, sinistro.getDataSinistro().toString());
            stmt.setString(3, sinistro.getDescricao());
            stmt.setBoolean(4, sinistro.isStatus());
            stmt.setInt(5, sinistro.getIdSeguro());
            stmt.setInt(6, sinistro.getIdSinistro());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** deleta um servico
	 * @param int - id do sinistro
	 * @param int - id do seguro
	 * @param int - id do cliente
	 * */
    public void delete(int idSinistro, int idSeguro, int idCliente) {
        String sql = "delete from T_SINISTRO where cd_sinistro = ? and cd_seguro = ? and cd_cliente = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSinistro);
            stmt.setInt(2, idSeguro);
            stmt.setInt(3, idCliente);

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** atualiza um sinistro
	 * @param Sinistro - sinistro
	 * */
    public void update(Sinistro sinistro) {
        String sql = "update T_SINISTRO set tp_sinistro = ?, dt_sinistro = ?, ds_sinistro = ?, status = ? where cd_sinistro = ? and cd_seguro = ? and cd_cliente = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, sinistro.getTipoSinistro());
            stmt.setDate(2, sinistro.getDataSinistro());
            stmt.setString(3, sinistro.getDescricao());
            stmt.setBoolean(4, sinistro.isStatus());
            stmt.setInt(5, sinistro.getIdSinistro());
            stmt.setInt(6, sinistro.getIdSeguro());
            stmt.setInt(7, sinistro.getIdSinistro());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** seleciona todos os sinistros
	 * @param List<Sinistro> - lista de sinistros
	 * */
    public List<Sinistro> selectAll() {
        String sql = "select * from T_SINISTRO order by cd_sinistro";
        List<Sinistro> listSinistros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sinistro sinistro = new Sinistro();

                sinistro.setIdSinistro(rs.getInt("cd_sinistro"));
                sinistro.setTipoSinistro(rs.getString("tp_sinistro"));
                sinistro.setDataSinistro(rs.getDate("dt_sinistro"));
                sinistro.setDescricao(rs.getString("ds_sinistro"));
                sinistro.setStatus(rs.getBoolean("status"));
                sinistro.setIdSeguro(rs.getInt("cd_seguro"));

                listSinistros.add(sinistro);
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSinistros;
    }

    /** seleciona um servico pelo id do sinistro, seguro e cliente
	 * @param int - id do sinistro
	 * @param int - id do seguro
	 * @param int - id do cliente
	 * @return Sinistro - sinistro
	 * */
    public Sinistro selectById(int idSinistro, int idSeguro, int idCliente) {
        String sql = "select * from T_SINISTRO where cd_sinistro = ? and cd_seguro = ? and cd_cliente = ?";
        Sinistro sinistro = new Sinistro();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSinistro);
            stmt.setInt(2, idSeguro);
            stmt.setInt(3, idCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sinistro.setIdSinistro(rs.getInt("cd_sinistro"));
                sinistro.setTipoSinistro(rs.getString("tp_sinistro"));
                sinistro.setDataSinistro(rs.getDate("dt_sinistro"));
                sinistro.setDescricao(rs.getString("ds_sinistro"));
                sinistro.setStatus(rs.getBoolean("status"));
                sinistro.setIdSeguro(rs.getInt("cd_seguro"));
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinistro;
    }
}
