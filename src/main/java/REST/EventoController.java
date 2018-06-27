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
@WebServlet(name = "Evento", urlPatterns = {"/Evento"}) //Mudar nome para EVENTO
public class EventoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /** VERIFICAR com o Watanabe se a chama para a classe do cache é nesse
         * classe ou se é feito no jquery do html/servlet
         */
        String nomeBusca = request.getParameter("busca");
        System.out.println("estou na busca");
        List<Evento> listaEventos = null;
        if (nomeBusca != null && !nomeBusca.trim().equals("")) {
            listaEventos = new EventoDAO_JPA().getBuscaEvento(nomeBusca);
        } else {
            listaEventos = new EventoDAO_JPA().getListaEventos();
        }
        System.out.println("Busca realizada");
        response.setContentType("application/json");
        response.getWriter().print((new Gson()).toJson(listaEventos));
        
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
