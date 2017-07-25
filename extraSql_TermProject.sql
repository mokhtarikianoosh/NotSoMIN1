 CREATE TABLE `User`(
    `Username` varchar(255) NOT NULL,
    `Password` varchar(50) DEFAULT NULL,
    `Email` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`Username`)
)ENGINE=InnoDB;


create table companyIssue (
	company varchar(255),
    issue varchar(255),
    numOfIssue int

)ENGINE=InnoDB;



create table productIssue (
	product varchar(255),
    issue varchar(255)

)ENGINE=InnoDB;

create table issuesByState (
	company varchar(255),
    state varchar(255),
    issue varchar(255),
    total int

)ENGINE=InnoDB;


create index userName on User(Username);

insert into companyIssue(company,issue,numOfIssue) select name, issue, count(issue) as number from complaint4 inner join company3 on id = complaintid group by name,issue order by name asc;


insert into productIssue(product,issue) select product, issue from complaint4 inner join company3 on id = complaintId;


insert into issuesByState (company,state,issue,total) select * from (select name, state, issue, count(issue) as total from company3 inner join complaint4 on complaintId = id group by name, state,issue order by total desc) as m;
