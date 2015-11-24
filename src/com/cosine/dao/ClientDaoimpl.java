package com.cosine.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cosine.domain.Client;
import com.cosine.domain.Student;

public class ClientDaoimpl implements BeanDao<Client>{
	private ConnectUtils con = new ConnectUtils();
	private ArrayList<Client> clientsList = null;
	private JSONArray jarr = null;
	private static ClientDaoimpl instance = null;
	private ClientDaoimpl(){}
	
	
	public String getPath(){
		String webroot = ClientDaoimpl.class.getResource("").getFile();
		return webroot.substring(0, webroot.indexOf("com"))+"user.json";
	}
	
	public void load(){
   	 	jarr = null;
		if(getPath()==null||getPath().trim()==""){
			throw new RuntimeException("设置的默认加载路径有误");
		}	
		String jsondata = con.readJson(getPath());
		jarr = JSONArray.fromObject(jsondata);
		clientsList = null;
		clientsList = new ArrayList<Client>(jarr.size());
		for(int i = 0;i<jarr.size();i++){
	         JSONObject obj = JSONObject.fromObject(jarr.get(i));
	         clientsList.add((Client) JSONObject.toBean(obj, Client.class));
		}

	}
	public boolean isUserExist(Client c){
		return clientsList.contains(c);
	}
	public boolean isUserExist(String username){
		Client c = new Client();
		c.setUsername(username);
		return clientsList.contains(c);
	}
	public boolean checkPassword(Client c){
		if(isUserExist(c)){
			int index = clientsList.indexOf(c);
			Client data = clientsList.get(index);
			if(data.getPassword().equals(c.getPassword())){
				return true;
			}else{
				return false;
			}	
		}else{
			return false;
		}
	}
	public int getPower(Client c){
		if(isUserExist(c)){
			int index = clientsList.indexOf(c);
			Client data = clientsList.get(index);
			if(data.getPassword().equals(c.getPassword())){
				return data.getPower();
			}	
		}
		return 3;
	}

	public void commit(){
		JSONArray data = JSONArray.fromObject(clientsList);
		con.writeJson(getPath(), data.toString());
	}


	@Override
	public boolean insert(Client c) {
		clientsList.add(c);
		return true;
	}


	@Override
	public boolean delete(Client c) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean edit(Client c) {
		// TODO Auto-generated method stub
		return true;

	}


	@Override
	public Student query(Client c) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Client> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setPath(String path) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		
	}
	public static ClientDaoimpl getInstance(){
		if(instance==null){
			instance = new ClientDaoimpl();
		}
		return instance;
	}
}
