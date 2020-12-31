load data infile 'CATEGORY.csv'
 into table CATEGORY
 fields terminated by "," optionally enclosed by '"'		  
 ( code, description, parentcode )