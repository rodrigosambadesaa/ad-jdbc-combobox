# Enunciado original — eliminar artículos con JDBC y JComboBox

Fuente: `Ejercicio.pdf` del bloque **Acceso a BD con JDBC** conservado en Google Drive.

## Ejercicio

Entra en phpMyAdmin y crea una base de datos llamada **`tienda`**. A continuación, abre la base de datos e importa el archivo **`tienda.sql`**. Comprueba que se han creado correctamente las tablas de artículos, marcas y familias.

Crea un programa que permita al usuario **eliminar un artículo en base al código del artículo**.

- En un `ComboBox` aparecerá la lista de todos los códigos de artículos disponibles.
- Al seleccionar un código aparecerán los datos del artículo.
- Si ese es el artículo que se quiere eliminar, se pulsará el botón **Eliminar**.

### Sentencias SQL indicadas en el enunciado

Para obtener los diferentes códigos de los artículos:

```sql
SELECT codigo FROM articulos;
```

Para obtener los datos de un artículo una vez seleccionado el código:

```sql
SELECT marcas.nombre, modelo, articulos.descripcion, precio, descuento, familias.codigo
FROM articulos
INNER JOIN marcas ON articulos.cod_marca = marcas.codigo
INNER JOIN familias ON articulos.cod_familia = familias.codigo
WHERE articulos.codigo = ?;
```

Para borrar el artículo seleccionado:

```sql
DELETE FROM articulos WHERE codigo = ?;
```

La base de datos original requerida por el ejercicio se conserva en [`database/tienda.sql`](database/tienda.sql).
