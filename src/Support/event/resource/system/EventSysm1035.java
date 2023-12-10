package Support.event.resource.system;

import com.sideinternational.sas.event.Event;
import com.sideinternational.sas.event.logging.Logger;
/**
 * User xxx Executed no yyyy 
 * @author Administrator
 *
 */
public class EventSysm1035  extends Event {

	public EventSysm1035(String[] szErrorMessage, Throwable t) 
	{
		super(szErrorMessage, t);
		Logger.log(this);
	}

}
