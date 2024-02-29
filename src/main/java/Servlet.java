import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet para la autenticación de usuarios.
 * 
 * Este servlet maneja las solicitudes de inicio de sesión de los usuarios.
 * Valida las credenciales de inicio de sesión y redirecciona a la página de bienvenida
 * si las credenciales son válidas, o muestra un mensaje de error si no lo son.
 * 
 * @author [Tu nombre]
 * @version 1.0
 */

@WebServlet("/LoginServlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * Conecta a la base de datos.
     * 
     * @return la conexión a la base de datos
     * @throws Exception si ocurre un error al conectar a la base de datos
     */

    private Connection connectToDatabase() throws Exception {
        String url = "jdbc:mysql://localhost/final_bbdd";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * Valida las credenciales de inicio de sesión.
     * 
     * @param user el nombre de usuario
     * @param pass la contraseña
     * @return true si las credenciales son válidas, false de lo contrario
     * @throws Exception si ocurre un error al validar las credenciales
     */

    private String[] getUserInfo(String user, String pass) throws Exception {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("SELECT nombre, apellidos FROM usuarios WHERE username = ? AND password = ?");
        statement.setString(1, user);
        statement.setString(2, pass);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            return new String[]{nombre, apellidos};
        } else {
            return null; // Si no se encuentra el usuario, devolvemos null
        }
    }

    
    /**
     * Maneja las solicitudes POST para iniciar sesión.
     * 
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error de servlet
     * @throws IOException si ocurre un error de entrada/salida
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            String[] userInfo = getUserInfo(username, password);
            if (userInfo != null) {
                // Si las credenciales son válidas, redireccionamos a la página de bienvenida con los parámetros
                String nombre = userInfo[0];
                String apellidos = userInfo[1];
                response.sendRedirect("welcome.jsp?nombre=" + nombre + "&apellidos=" + apellidos);
            } else {
                // Si las credenciales no son válidas, redireccionamos a la página de inicio de sesión
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            throw new ServletException("Error validating user credentials", e);
        }
    }
}
