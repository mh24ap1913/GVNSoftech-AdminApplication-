package in.co.brings.loginDemo;

import java.util.HashMap;

public class LoginService {
	LoginDao dao=new LoginDao();
	public int signUp(LoginBean bean)throws Exception{
		
		
		return dao.signUp(bean);
		
	}
	public int loginUser(HashMap<String, String> input)throws Exception{
		return  dao.loginUser(input);
		
	}

}
