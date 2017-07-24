package Entity;

public class BreakdownIssue {
	private String product;
	private String issue;
	private int totalIssues;
	
	
	public BreakdownIssue(String product, String issue, int totalIssues) {
		this.product = product;
		this.issue = issue;
		this.totalIssues = totalIssues;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getIssue() {
		return issue;
	}


	public void setIssue(String issue) {
		this.issue = issue;
	}


	public int getTotalIssues() {
		return totalIssues;
	}


	public void setTotalIssues(int totalIssues) {
		this.totalIssues = totalIssues;
	}
	
	
}
