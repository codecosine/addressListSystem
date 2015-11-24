package com.cosine.services;
import com.cosine.dao.ClientDaoimpl;
import com.cosine.domain.Client;

public class ClientServices {

	private ClientDaoimpl dao = ClientDaoimpl.getInstance();
	private static ClientServices instance = null;
	private ClientServices(){}
	public void load(){
		dao.load();
	}
	public void resister(Client c){
		dao.insert(c);
		dao.commit();
	}
	public boolean check(Client c){
		return dao.checkPassword(c);
	}
	public int getPower(Client c){
		return dao.getPower(c);
	}
	public boolean isExistUser(String username){
		return dao.isUserExist(username);
	}
	public static ClientServices getInstance() {
		if(instance==null){
			instance = new ClientServices();
		}
		return instance;
	}
	
	
}
