import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weiwei.svr.dao.IAnnouncementDAO;

public class test {

	public static void main( String[] args )
    {
    	ApplicationContext context = 
    		new ClassPathXmlApplicationContext("Infra-Module.xml");
 
        IAnnouncementDAO announcementDAO = (IAnnouncementDAO) context.getBean("announcementDAO");
        
        List<?> announcementList = announcementDAO.findBySequenceId(1, 2);
        System.out.println(announcementList);
 
    }
}
