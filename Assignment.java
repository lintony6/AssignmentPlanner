import java.util.ArrayList;
public class Assignment {
ArrayList<String> assignments = new ArrayList<String>();
public void removeDate(int o) {
	for(int i = 0;i<assignments.size();++i) {
		 int combine = getFirstNum(assignments.get(i));
		if(o==combine) {
			assignments.remove(i);
		}
	}
}
public void addHw(String s) {
	assignments.add(s);
}
public String getHw(int i) {
	return assignments.get(i).toString();
}
public int size() {
	return assignments.size();
}
public int getFirstNum(String s) {
	int num;
	try{ 
		String shorter = Character.toString(s.charAt(0))+Character.toString(s.charAt(1));
		num = Integer.parseInt(shorter);
	}
	catch(Exception e) {
		String shorter = Character.toString(s.charAt(0));
		num = Integer.parseInt(shorter);
	}
	return num;
}
public void sortArray() {
	try {
	int i=1;
	int j;
	String holder;
		while(i<assignments.size()) {
			j=i;
			while((j>0)&&((getFirstNum(assignments.get(j-1)))>getFirstNum(assignments.get(j)))) { 
				holder = assignments.get(j-1);
				assignments.set(j-1, assignments.get(j));
				assignments.set(j, holder); 
				j=j-1;	
			}
			i=i+1;
			}
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
		}

}