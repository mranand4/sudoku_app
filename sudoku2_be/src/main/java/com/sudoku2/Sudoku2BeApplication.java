package com.sudoku2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 
 * @author shivansh
 * common error 1 : when we have mysql connector but not using it :https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd
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
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Sudoku2BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sudoku2BeApplication.class, args);
	}

}
