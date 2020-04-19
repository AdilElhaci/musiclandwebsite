
package filter;

import controller.RseimDocumentControllers;
import controller.SarkiDocmentControllers;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FileServelt", urlPatterns = {"/file/*"})
public class FileServelt extends HttpServlet {

    @Inject
    private SarkiDocmentControllers sarki_doc;
    @Inject
    private RseimDocumentControllers resim_yol;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String file = request.getPathInfo(); 
        if(file.contains("mp3") ||file.contains("mp4") || file.contains("3gp")){
        File f = new File(sarki_doc.getUploadTo()+file);
        Files.copy(f.toPath(), response.getOutputStream());
        }
        else if(file.contains("jpg") || file.contains("png")){
        File f = new File(resim_yol.getUploadTo()+file);
        Files.copy(f.toPath(), response.getOutputStream());
        }
        
    }

   
}
