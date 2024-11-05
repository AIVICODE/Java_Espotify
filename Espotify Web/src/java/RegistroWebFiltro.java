
import Logica.Fabrica;
import Logica.IControlador;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegistroWebFiltro implements Filter {
    
           Fabrica fabrica = Fabrica.getInstance();
IControlador control = fabrica.getIControlador();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        

        String ip = request.getRemoteAddr();
        String url = httpRequest.getRequestURL().toString();
        String browser = httpRequest.getHeader("User-Agent");
        String so = System.getProperty("os.name");
        
        control.GeneraRegistro(ip, url, browser, so);
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Aquí puedes hacer cualquier limpieza necesaria
    }
}
