package dev.rodrigosambade.jdbc;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public final class ArticleBrowserApp {
    public static void main(String[] args){
        SwingUtilities.invokeLater(ArticleBrowserApp::show);
    }
    private static void show(){
        try(Connection c=DemoDatabase.open()){
            ArticleRepository repo=new ArticleRepository();
            var articles=repo.findAll(c);
            JComboBox<Article> combo=new JComboBox<>(articles.toArray(Article[]::new));
            JTextArea details=new JTextArea(8,45);
            details.setEditable(false);
            Runnable refresh=()->{
                Article a=(Article)combo.getSelectedItem();
                details.setText(a==null?"":format(a));
            };
            combo.addActionListener(e->refresh.run());
            refresh.run();
            JPanel p=new JPanel(new BorderLayout(8,8));
            p.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
            p.add(combo,BorderLayout.NORTH);
            p.add(new JScrollPane(details));
            JFrame f=new JFrame("Artículos JDBC");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.add(p);
            f.pack();
            f.setLocationByPlatform(true);
            f.setVisible(true);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"JDBC",JOptionPane.ERROR_MESSAGE);
        }
    }
    private static String format(Article a){
        return "Código: "+a.id()+"\nMarca: "+a.brand()+"\nModelo: "+a.model()+"\nDescripción: "+a.description()+"\nPrecio: "+a.price()+"\nDescuento: "+a.discount()+"\nFamilia: "+a.family();
    }
}
