package dev.rodrigosambade.jdbc;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public final class ArticleBrowserApp {

    private ArticleBrowserApp() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArticleBrowserApp::show);
    }

    private static void show() {
        try (Connection connection = DemoDatabase.open()) {
            ArticleRepository repository = new ArticleRepository();
            List<Article> articles = repository.findAll(connection);
            JFrame frame = createFrame(articles);
            frame.setVisible(true);
        } catch (SQLException exception) {
            showDatabaseError(exception);
        }
    }

    private static JFrame createFrame(List<Article> articles) {
        JComboBox<Article> articleSelector = new JComboBox<>(articles.toArray(Article[]::new));
        JTextArea details = new JTextArea(8, 45);
        details.setEditable(false);

        Runnable refresh = () -> refreshDetails(articleSelector, details);
        articleSelector.addActionListener(event -> refresh.run());
        refresh.run();

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(articleSelector, BorderLayout.NORTH);
        content.add(new JScrollPane(details), BorderLayout.CENTER);

        JFrame frame = new JFrame("Artículos JDBC");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(content);
        frame.pack();
        frame.setLocationByPlatform(true);
        return frame;
    }

    private static void refreshDetails(
            JComboBox<Article> articleSelector,
            JTextArea details) {
        Article article = (Article) articleSelector.getSelectedItem();
        details.setText(article == null ? "" : format(article));
    }

    private static String format(Article article) {
        return """
                Código: %d
                Marca: %s
                Modelo: %s
                Descripción: %s
                Precio: %s
                Descuento: %s
                Familia: %s
                """.formatted(
                article.id(),
                article.brand(),
                article.model(),
                article.description(),
                article.price(),
                article.discount(),
                article.family());
    }

    private static void showDatabaseError(SQLException exception) {
        JOptionPane.showMessageDialog(
                null,
                exception.getMessage(),
                "JDBC",
                JOptionPane.ERROR_MESSAGE);
    }
}
