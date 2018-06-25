package REST;

import com.google.gson.Gson;
import Model.Evento;
import Model.EventoDAO_JPA;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author White
 */
@WebServlet(name = "BuscaRest", urlPatterns = {"/BuscaRest"})
public class BuscaREST extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Cheguei no controler");
        String nomeBusca = request.getParameter("busca");
        List<Evento> listaEventos = null;
        if (nomeBusca != null && !nomeBusca.trim().equals("")) {
            listaEventos = new EventoDAO_JPA().getBuscaEvento(nomeBusca);
        } else {
            listaEventos = new EventoDAO_JPA().getListaEventos();
        }
        response.setContentType("application/json");
        //response.getWriter().print((new Gson()).toJson(listaEventos));
        String gsonResult = (new Gson()).toJson(listaEventos);
        System.out.println("Retorno controller: "+ gsonResult);
        request.setAttribute("listaEventos", gsonResult);
        request.getRequestDispatcher("WEB-INF/view/IndexMod.jsp").forward(request, response);
        return;
    }

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
