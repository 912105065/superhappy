package star.superhappy.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 此Action用来完成web-inf中jsp与jsp请求转发的功能，此Action不处理任何的逻辑
 * @author Gang
 */
public class SendAction extends ActionSupport {

	//单例，在启动时完成实体化
	public SendAction(){
		System.out.println("---SendAction---");
	}
	
	public String execute(){
		System.out.println("---execute---");
		return "send";
	}
}
