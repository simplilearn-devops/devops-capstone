package devops.mvc;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import devops.business.DataManager;

@Controller
public class MonitoringController {
	private Logger logger = LoggerFactory.getLogger( getClass() ) ;
	private DataManager dataManager ;

	@RequestMapping(value="index.html",method=RequestMethod.GET)
    public ModelAndView home()
    {
		logger.info( "Home" ) ;
        return new ModelAndView( "index" ) ;
    }
	
	@Autowired
	public void setDataManager(DataManager dataManager) {
		logger.info( "Data Manager Wired" ) ;
		this.dataManager = dataManager;
	}

	@RequestMapping(value="monitoring.html",method=RequestMethod.GET)
	public ModelAndView monitoring() {
		Map<String,Object> model = new HashMap<>() ;
		model.put( "title", dataManager.getTitle() ) ;
		model.put( "dockerData",  dataManager.getDockerData() ) ;
		return new ModelAndView( "monitoring", "model", model ) ;
	}
}
