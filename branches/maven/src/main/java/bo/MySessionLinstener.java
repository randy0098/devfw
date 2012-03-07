package bo;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionLinstener
 *
 */
public class MySessionLinstener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionLinstener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent httpsessionevent) {
        // TODO Auto-generated method stub
    	System.out.println("sessionCreated:"+new Date());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
        // TODO Auto-generated method stub
    	System.out.println("sessionDestroyed:"+new Date());
    }
	
}
