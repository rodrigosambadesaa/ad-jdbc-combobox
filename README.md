# JDBC + JComboBox — consulta de artículos

Reimplementación de `PracticaJDBCComboBoxAccesoADatos` (2018). La entrega original accedía a MySQL desde el `ActionListener`, concatenaba el código seleccionado en la consulta y mantenía toda la lógica dentro de `Ventana.java`.

Esta versión usa un DAO, `PreparedStatement`, `try-with-resources`, un modelo `Article` inmutable y una base H2 de demostración autocontenida. Puede apuntarse a otro JDBC mediante `-Ddb.url`, `-Ddb.user` y `-Ddb.password`.

Ejecute `dev.rodrigosambade.jdbc.ArticleBrowserApp`.
