package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author White
 */
public class EventoDAO {

    private final Connection connection;

    public EventoDAO() throws ClassNotFoundException {
        this.connection = ConnectionFactory.getConnection();
    }

    public int adicionaEvento(Evento evento) {
        String sql = "insert into evento (name_event, date, id_user)"
                + " VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, evento.getNome());
            ps.setTimestamp(2, new java.sql.Timestamp(evento.getData().getTime()));
            ps.setLong(3, evento.getUserId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ps.close();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Evento> getListaEventos() {
        try {
            List<Evento> eventos = new ArrayList<Evento>();

            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM evento");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evento event = new Evento();
                event.setNome(rs.getString("name_event"));
                event.setUserId(rs.getInt("id_user"));
                event.setData(rs.getDate("date"));
                eventos.add(event);
            }
            ps.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Evento> buscaEvento(String nomeEvento) {
        String sql = "SELECT * FROM evento WHERE name_event LIKE ?";
        try {
            List<Evento> eventos = new ArrayList();
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, "%" + nomeEvento + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Evento event = new Evento();
                event.setNome(rs.getString("name_event"));
                event.setData(rs.getDate("date"));
                event.setUserId(rs.getInt("id_user"));
                eventos.add(event);
            }
            ps.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
