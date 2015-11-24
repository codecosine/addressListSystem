package com.cosine.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cosine.domain.Student;
/**
 * 使用了JSON数据作为数据存取类型
 * 封装了JSON-LIB的方法,使用ConnectUtils进行转换
 *
 */
public class StudentDaoimpl implements BeanDao<Student>{
	private ConnectUtils con = new ConnectUtils();
	private String sclassPath;
	private ArrayList<Student> studentsList = null;
	private ArrayList<Student> studentsListAll = null;
	private JSONArray jarr = null;
	private static StudentDaoimpl instance = null;
	private StudentDaoimpl(){}
	
	@Override
	public String getPath(){
		if(sclassPath==null||sclassPath.trim()==""){
			sclassPath = "studentsData_classall.json";
		}
		String webroot = ClientDaoimpl.class.getResource("").getFile();
		return webroot.substring(0, webroot.indexOf("com"))+sclassPath;
	}
	@Override
	public void setPath(String path) {
		this.sclassPath = "studentsData_class"+path+".json";
	}
	/**
	 * 加载全部学生
	 * 一开始就加载
	 */
	public void loadAllStudent(){
		if(studentsListAll!=null){
			studentsListAll.clear();
			studentsListAll=null;
		}
		
		String jsondata = con.readJson(getPath());
		JSONArray jarrall = JSONArray.fromObject(jsondata);
		studentsListAll = new ArrayList<Student>(jarrall.size());
		for(int i = 0;i<jarrall.size();i++){
	         JSONObject obj = JSONObject.fromObject(jarrall.get(i));
	         studentsListAll.add((Student) JSONObject.toBean(obj, Student.class));
		}
	}
	public void loadStudent(){
		if(studentsList!=null){
			studentsList.clear();
			studentsList=null;
		}
		String jsondata = con.readJson(getPath());
		JSONArray jarrall = JSONArray.fromObject(jsondata);
		studentsList = new ArrayList<Student>(jarrall.size());
		for(int i = 0;i<jarrall.size();i++){
	         JSONObject obj = JSONObject.fromObject(jarrall.get(i));
	         studentsList.add((Student) JSONObject.toBean(obj, Student.class));
		}
	}
	
	/**
	 * 将JSON文件加载为JSONArray
	 */
	@Override
	public void load(){
   	 	jarr = null;
		if(getPath()==null||getPath().trim()==""){
			throw new RuntimeException("你还没有设置加载文件路径");
		}	
		String jsondata = con.readJson(getPath());
		jarr = JSONArray.fromObject(jsondata);
	}
	/**
	 * 开始一个事务
	 * 读取jarr中的内容，转换为当前操作的arraylist
	 */
	@Override
	public void start(){
		if(studentsList!=null){
			throw new RuntimeException("你的上一个事务还没有提交,请确认后在执行下一个操作");
		}
		String jsondata = con.readJson(getPath());
		jarr = JSONArray.fromObject(jsondata);
		studentsList = new ArrayList<Student>(jarr.size());
		for(int i = 0;i<jarr.size();i++){
	         JSONObject obj = JSONObject.fromObject(jarr.get(i));
	         studentsList.add((Student) JSONObject.toBean(obj, Student.class));
		}
		 
	}
	/**
	 * 将arraylist的内容写成JSON文件
	 */
	@Override
	public void commit(){
		JSONArray data = JSONArray.fromObject(studentsList);
		con.writeJson(getPath(), data.toString());
		studentsList.clear();
		studentsList = null;
		sclassPath = null;
		jarr.clear();
		jarr = null;
	}
	/**
	 * 抹除当前的操作
	 * 回滚到加载jarr的时候
	 */
	@Override
	public void rollback(){
		if(studentsList != null){
			studentsList.clear();
			studentsList = null;
		}
	}

	@Override
	public boolean insert(Student c) {
		if(studentsList==null){
			return false;
		}
		studentsList.add(c);
		return true;
	}

	@Override
	public boolean delete(Student c) {
		if(studentsList==null){
			return false;
		}
		int index = studentsList.indexOf(c);
		if(index>0){
			studentsList.remove(index);
			return true;
		}
		return false;
		
	}

	/**
	 * 使用编辑方法时候要注意传入的参数是要修改的学生
	 * 会根据学生的id进行和原来数据的匹配
	 * id是学生之间比较的唯一标示
	 */
	@Override
	public boolean edit(Student c) {
		if(studentsList==null){
			return false;		
		}
		int index = studentsList.indexOf(c);//这里的indexof是根据id进行的匹配
		studentsList.set(index, c);
		return true;
	}

	/**
	 * 查询方法有待改进
	 * 应该增加多种查询条件
	 */
	@Override
	public Student query(Student c) {
		if(studentsListAll==null){
			loadAllStudent();
		}
		int index = studentsListAll.indexOf(c);
		return studentsListAll.get(index);
	}
	public String queryStuclass(String stuID){
		Student stu = new Student();
		stu.setId(stuID);
		Student getStu = query(stu);
		System.out.println("找到学生班级了");
		return getStu.getSclass();
		
	}

	@Override
	public List<Student> queryAll() {
		if(studentsListAll==null){
			loadAllStudent();
		}
		return studentsListAll;
		
	}
	public List<Student> getLocalList() {
		
		return studentsList;
	}
	public static StudentDaoimpl getInstance(){
		if(instance==null){
			instance = new StudentDaoimpl();
		}
		return instance;
	}


}
