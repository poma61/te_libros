package com.emergentes.controlador;

import com.emergentes.modelo.Libro;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Libro> lista = new ArrayList<Libro>();
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {
                //para listar los datos
                String sql = "SELECT * FROM libros";
//Consulta a la base de datos
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Libro lib = new Libro();
                    lib.setId(rs.getInt("id"));
                    lib.setIsbn(rs.getString("isbn"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setCategoria(rs.getString("categoria"));
                    lista.add(lib);

                }//while
                request.setAttribute("lista", lista);
                //enviar al index.jsp para mostrar la informacion
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }  //list

            if (op.equals("nuevo")) {
                //Instanciar un objeto de la clase libro
                Libro li = new Libro();
                //el objeto se pone como atributo request
                request.setAttribute("lib", li);
                //Redireccionar a editar.jsp
                request.getRequestDispatcher("editar.jsp").forward(request, response);

            }
            if (op.equals("eliminar")) {
                //obtener el id
                int id = Integer.parseInt(request.getParameter("id"));
                //Realizxar la eliminacion de la base de datos
                //1ra forma
                String sql = "DELETE FROM libros WHERE id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                //2da forma
                /*  String sql ="DELETE FROM libros WHERE id="+id;
         ps=conn.prepareStatement (sql);
          ps.executeUpdate();*
                 */
                //Redireccionar a MainController
                response.sendRedirect("MainController");
            }
             if(op.equals("editar")){
                int id=Integer.parseInt(request.getParameter("id"));
                String sql="SELECT * FROM libros WHERE id="+id; 
                 ps=conn.prepareStatement(sql);
                 rs=ps.executeQuery();
                Libro lib = new Libro();
                  while(rs.next()){
                    lib.setId(rs.getInt("id"));
                    lib.setIsbn(rs.getString("isbn"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setCategoria(rs.getString("categoria"));
                 
                  }
                  request.setAttribute("op", op);
                  request.setAttribute("lib",lib);
                  request.getRequestDispatcher("editar.jsp").forward(request,response);
             }
            canal.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de base de datos:" + ex.getMessage());
        }

    }//metodo doGet

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String isbn = request.getParameter("isbn");
            String titulo = request.getParameter("titulo");
            String categoria = request.getParameter("categoria");

            Libro lib = new Libro();
            lib.setId(id);
            lib.setIsbn(isbn);
            lib.setTitulo(titulo);
            lib.setCategoria(categoria);
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (id == 0) {
                //nuevo registro
                String sql = "INSERT INTO libros(isbn,titulo,categoria) VALUES(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, lib.getIsbn());
                ps.setString(2, lib.getTitulo());
                ps.setString(3, lib.getCategoria());
                ps.executeUpdate();

            } else {
                //edicion de registro 
                String sql="UPDATE libros set isbn='"+lib.getIsbn()+"',titulo='"+lib.getTitulo()+"',categoria='"+lib.getCategoria()+"' WHERE id="+lib.getId();
                ps=conn.prepareStatement(sql);
                ps.executeUpdate();
            
            }
            response.sendRedirect("MainController");

        } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Error de la base de datos"+ex.getMessage());
        }

    }

}
