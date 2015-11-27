import java.util.List;


public class Policy {
	private String policyNumber;
	private String subject;
	private List<String> goal;
	private List<String> scope;
	private List<String> regulation;
	private List<String> constraints;
	private List<String> juridiction;
	
	/**
	 * @return the policyNumber
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}
	/**
	 * @param policyNumber the policyNumber to set
	 */
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	/**
	 * @return the goal
	 */
	public List<String> getGoal() {
		return goal;
	}
	/**
	 * @param goal the goal to set
	 */
	public void setGoal(List<String> goal) {
		this.goal = goal;
	}
	/**
	 * @return the scope
	 */
	public List<String> getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(List<String> scope) {
		this.scope = scope;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the regulation
	 */
	public List<String> getRegulation() {
		return regulation;
	}
	/**
	 * @param regulation the regulation to set
	 */
	public void setRegulation(List<String> regulation) {
		this.regulation = regulation;
	}
	/**
	 * @return the constraints
	 */
	public List<String> getConstraints() {
		return constraints;
	}
	/**
	 * @param constraints the constraints to set
	 */
	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}
	/**
	 * @return the juridiction
	 */
	public List<String> getJuridiction() {
		return juridiction;
	}
	/**
	 * @param juridiction the juridiction to set
	 */
	public void setJuridiction(List<String> juridiction) {
		this.juridiction = juridiction;
	}
	
	

	
	
	
}
