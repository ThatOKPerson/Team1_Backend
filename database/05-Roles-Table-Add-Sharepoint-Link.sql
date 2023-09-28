use Team1_KelvinC;
ALTER TABLE  Roles
ADD sharepointLink varchar(400);

UPDATE Roles
SET sharepointLink = 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Lead%20Software%20Engineer%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'
WHERE roleId = 1;

UPDATE Roles
SET sharepointLink = 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FPlatforms%2FJob%20profile%20%2D%20Principal%20Platform%20Architect%20%28Principal%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FPlatforms&p=true&ga=1'
WHERE roleId = 2;

UPDATE Roles
SET sharepointLink = 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FPeople%2FPeople%20Support%2FJob%20profile%20%2D%20People%20Support%20Associate%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FPeople%2FPeople%20Support&p=true&ga=1'
WHERE roleId = 3;

UPDATE Roles
SET sharepointLink = 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'
WHERE roleId = 4;
