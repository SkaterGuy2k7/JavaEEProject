package sheridan;

public class Material {
	private int matId;
	private int studId;
	private int courseId;
	private String grade;
	private String matWeight;
	private String matName;
	private String matType;
	
	public int getMatId() {
		return matId;
	}
	public int getStudId() {
		return studId;
	}
	public int getCourseId() {
		return courseId;
	}
	public String getGrade() {
		return grade;
	}
	public String getMatWeight() {
		return matWeight;
	}
	public String getMatName() {
		return matName;
	}
	public String getMatType() {
		return matType;
	}
	public void setMatId(int matId) {
		this.matId = matId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setMatWeight(String matWeight) {
		this.matWeight = matWeight;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public void setMatType(String matType) {
		this.matType = matType;
	}
}
