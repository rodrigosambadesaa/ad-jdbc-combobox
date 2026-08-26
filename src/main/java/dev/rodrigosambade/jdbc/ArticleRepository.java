package dev.rodrigosambade.jdbc;
import java.sql.*;
import java.util.*;
public final class ArticleRepository {
    private static final String SELECT = "select a.codigo,m.nombre,a.modelo,a.descripcion,a.precio,a.descuento,f.nombre from articulos a join marcas m on m.codigo=a.cod_marca join familias f on f.codigo=a.cod_familia";
    public List<Article> findAll(Connection c) throws SQLException {
        try (PreparedStatement ps=c.prepareStatement(SELECT+" order by a.codigo"); ResultSet rs=ps.executeQuery()) {
            List<Article> out=new ArrayList<>();
            while(rs.next()) out.add(map(rs));
            return List.copyOf(out);
        }
    }
    public Optional<Article> findById(Connection c,int id) throws SQLException {
        try(PreparedStatement ps=c.prepareStatement(SELECT+" where a.codigo=?")){
            ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next()?Optional.of(map(rs)):Optional.empty();
            }
        }
    }
    private Article map(ResultSet rs) throws SQLException {
        return new Article(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBigDecimal(5),rs.getBigDecimal(6),rs.getString(7));
    }
}
