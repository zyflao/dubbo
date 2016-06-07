package Spring.Util;

import java.math.BigDecimal;

public class BigDecimalTest {
	public static void main(String[] args) {
		
	
	BigDecimal totalGenLose = new BigDecimal("10.00");
	BigDecimal beginBalance = new BigDecimal("110.00");
	BigDecimal  totalYieldrate = totalGenLose.divide(beginBalance,2,BigDecimal.
			ROUND_HALF_UP);
	
	System.out.println(totalYieldrate);
	}}