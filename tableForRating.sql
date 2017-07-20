 
 
 create table companyIssue (
	company varchar(255),
    issue varchar(255),
    numOfIssue int

)ENGINE=InnoDB;
 


insert into companyIssue(company,issue,numOfIssue) select name, issue, count(issue) as number from complaint4 
inner join company3 on id = complaintid group by name,issue order by number desc;
