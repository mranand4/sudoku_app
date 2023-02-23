package com.sudoku2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 
 * @author shivansh
 * common error 1 : when we have mysql connector but not using it :https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd
 * https://thorben-janssen.com/hibernate-jpa-date-and-time/
 * https://stackoverflow.com/questions/9565996/difference-between-primary-key-and-unique-key#:~:text=Both%20Primary%20key%20and%20Unique,does%20allow%20one%20NULL%20value%20.&text=Save%20this%20answer.,-Show%20activity%20on
 * https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/
 * https://stackoverflow.com/questions/52769291/spring-boot-jpa-if-i-have-multiple-tables-with-same-column-name-can-i-put-in-sa
 * https://stackoverflow.com/questions/10240538/use-string-in-switch-case-in-java
 * https://www.petefreitag.com/item/466.cfm
 * https://roytuts.com/spring-data-jpa-in-clause-with-where-condition/
 * https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm#:~:text=Hibernate%20Query%20Language%20(HQL)%20is,turns%20perform%20action%20on%20database.
 */

/**
 * 
 * @author shivansh
 * resources : 
 * https://www.sudokuwiki.org/Sudoku_Creation_and_Grading.pdf
 * https://qqwing.com/
 * https://github.com/stephenostermiller/qqwing/blob/main/src/java/
 * https://github.com/oliver-pham/sudoku-api/
 * 
 * api/sudoku [difficulty, accesstoken]
 * /api/token [email, password] 
 * /api/generatetoken [email, password, exiry]
 * 
 * cli/sudoku
 *
 */
@SpringBootApplication
public class Sudoku2BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sudoku2BeApplication.class, args);
	}

}
