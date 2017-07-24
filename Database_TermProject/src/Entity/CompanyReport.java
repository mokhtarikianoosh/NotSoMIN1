package Entity;

public class CompanyReport {

		private String company;
		private String issue;
		private int total;
		
		public CompanyReport(){
			
		}

		
		public CompanyReport(String company, String issue, int total) {
			this.company = company;
			this.issue = issue;
			this.total = total;
		}


		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getIssue() {
			return issue;
		}

		public void setIssue(String issue) {
			this.issue = issue;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	
		
			
		
		
}
