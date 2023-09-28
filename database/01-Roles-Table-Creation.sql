USE Team1_KelvinC;
CREATE TABLE Roles (
	roleId int PRIMARY KEY AUTO_INCREMENT,
    capability varchar(50),
    jobFamily varchar(50),
    jobProfileTitle  varchar(50),
    managementLevel varchar(50),
    description varchar(5000),
    minimalEssentialRequirements varchar(1000)
);
