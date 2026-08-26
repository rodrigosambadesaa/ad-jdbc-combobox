package dev.rodrigosambade.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ArticleRepository {

    private static final String SELECT_ARTICLES = """
            SELECT a.codigo,
                   m.nombre,
                   a.modelo,
                   a.descripcion,
                   a.precio,
                   a.descuento,
                   f.nombre
              FROM articulos a
              JOIN marcas m ON m.codigo = a.cod_marca
              JOIN familias f ON f.codigo = a.cod_familia
            """;

    public List<Article> findAll(Connection connection) throws SQLException {
        String sql = SELECT_ARTICLES + " ORDER BY a.codigo";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Article> articles = new ArrayList<>();
            while (resultSet.next()) {
                articles.add(map(resultSet));
            }
            return List.copyOf(articles);
        }
    }

    public Optional<Article> findById(Connection connection, int id) throws SQLException {
        String sql = SELECT_ARTICLES + " WHERE a.codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()
                        ? Optional.of(map(resultSet))
                        : Optional.empty();
            }
        }
    }

    private static Article map(ResultSet resultSet) throws SQLException {
        return new Article(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getBigDecimal(5),
                resultSet.getBigDecimal(6),
                resultSet.getString(7));
    }
}
