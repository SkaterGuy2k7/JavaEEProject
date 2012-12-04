package sheridan;

public class Course {
	private int courseId;
	private int profId;
	private int progId;
	private String courseName;
	private String couseCode;
	private String courseTime;
	private String roomNum;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getProfId() {
		return profId;
	}

	public void setProfId(int profId) {
		this.profId = profId;
	}

	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCouseCode() {
		return couseCode;
	}

	public void setCouseCode(String couseCode) {
		this.couseCode = couseCode;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
}
