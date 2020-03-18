package com.sdb;
/**
 * Asset.java
 * Brooke Lampe
 * 2017/02/10
 * This class summarizes and organizes Portfolio and prints them to the standard output
 */

public class Summary {
	
	public static double sumFees() {
		double fees = 0;
		for(int i = 0; i < Data.getPortfolioList().size(); i++) {
			fees = fees + Data.getPortfolioList().get(i).getFees();
		}
		return fees;
	}
	
	public static double sumFees(SortedList<Portfolio> portfolioSortedList) {
		double fees = 0;
		for(int i = 0; i < portfolioSortedList.getSize(); i++) {
			fees = fees + portfolioSortedList.get(i).getFees();
		}
		return fees;
	}

	public static double sumCommissions() {
		double commissions = 0;
		for(int i = 0; i < Data.getPortfolioList().size(); i++) {
			commissions = commissions + Data.getPortfolioList().get(i).getCommissions();
		}
		return commissions;
	}
	
	public static double sumCommissions(SortedList<Portfolio> portfolioSortedList) {
		double commissions = 0;
		for(int i = 0; i < portfolioSortedList.getSize(); i++) {
			commissions = commissions + portfolioSortedList.get(i).getCommissions();
		}
		return commissions;
	}
	
	public static double sumValue() {
		double value = 0;
		for(int i = 0; i < Data.getPortfolioList().size(); i++) {
			value = value + Data.getPortfolioList().get(i).getPortfolioValue();
		}
		return value;
	}
	
	public static double sumValue(SortedList<Portfolio> portfolioSortedList) {
		double value = 0;
		for(int i = 0; i < portfolioSortedList.getSize(); i++) {
			value = value + portfolioSortedList.get(i).getPortfolioValue();
		}
		return value;
	}
	
	public static double sumAnnualReturn() {
		double annualReturn = 0;
		for(int i = 0; i < Data.getPortfolioList().size(); i++) {
			annualReturn = annualReturn + Data.getPortfolioList().get(i).getPortfolioAnnualReturn();
		}
		return annualReturn;
	}
	
	public static double sumAnnualReturn(SortedList<Portfolio> portfolioSortedList) {
		double annualReturn = 0;
		for(int i = 0; i < portfolioSortedList.getSize(); i++) {
			annualReturn = annualReturn + portfolioSortedList.get(i).getPortfolioAnnualReturn();
		}
		return annualReturn;
	}
	
	public static double subRisk(Portfolio portfolio) {
		double risk = 0;
		for(int i = 0; i < portfolio.getAsset().size(); i++) {
			risk = risk + portfolio.getAsset().get(i).getRisk() * portfolio.getAsset().get(i).getActualValue();
		}
		if(subSumValue(portfolio) == 0) {
			return 0;
		}
		risk = risk / subSumValue(portfolio);
		return risk;
	}
	
	public static double subSumValue(Portfolio portfolio) {
		double value = 0;
		for(int i = 0; i < portfolio.getAsset().size(); i++) {
			value = value + portfolio.getAsset().get(i).getActualValue();
		}
		return value;
	}
	
	public static double subSumAnnualReturn(Portfolio portfolio) {
		double annualReturn = 0;
		for(int i = 0; i < portfolio.getAsset().size(); i++) {
			annualReturn = annualReturn + portfolio.getAsset().get(i).getAnnualReturn();
		}
		return annualReturn;
	}
	
	public static void printSummary() {
		String title = "Portfolio Summary Report";
		String singleLongerBreak = "------------------------------------------------------------------------------------------";
		String doubleBreak = "==============================================================================================================================================================";
		
		System.out.printf("%s\n", title);
		System.out.printf("%s\n", doubleBreak);
		System.out.printf("%-12s %-30s %-30s    %9s    %12s   %14s    %14s    %14s\n",
		"PORTFOLIO", "OWNER", "MANAGER", "FEES", "COMMISSIONS", "WEIGHTED RISK", "RETURN", "TOTAL");
		
		Data.sortPortfolioByOwner(Data.getPortfolioList());
		
		for(int i = 0; i < Data.getPortfolioList().size(); i++) {
			String printOwnerName;
			String printManagerName;
			
			try {
				printOwnerName = Data.getPortfolioList().get(i).getOwner().printName();
			} catch (NullPointerException e) {
				printOwnerName = "none";
			}
			
			try {
				printManagerName = Data.getPortfolioList().get(i).getManager().printName();
			} catch (NullPointerException e) {
				printManagerName = "none";
			}
			
			System.out.printf("%-12.12s %-30.30s %-30.30s   $%9.2f   $%12.2f   %14.4f    $%14.2f   $%14.2f\n",
			Data.getPortfolioList().get(i).getPortfolioCode(),
			printOwnerName,
			printManagerName,
			Data.getPortfolioList().get(i).getFees(),
			Data.getPortfolioList().get(i).getCommissions(),
			Data.getPortfolioList().get(i).getAggregateRiskMeasure(),
			Data.getPortfolioList().get(i).getPortfolioAnnualReturn(),
			Data.getPortfolioList().get(i).getPortfolioValue());
		}
		
		System.out.printf("%-12s %-30s %21s %s\n", "", "", "", singleLongerBreak);
		
		System.out.printf("%-12s %-30s %30s   $%9.2f   $%12.2f   %14s    $%14.2f   $%14.2f\n",
		"", "", "TOTALS:", sumFees(), sumCommissions(), "", sumAnnualReturn(), sumValue());
		
		System.out.printf("\n\n\n\n");
	}
	
	public static void printDetailReport() {
		String singleBreak = "--------------------------------------------";
		String singleLongBreak = "--------------------------------------------------------";
		String doubleBreak = "==============================================================================================================================================================";
		String detail = "Portfolio Details:";
		
		System.out.printf("%s\n", detail);
		System.out.printf("%s\n", doubleBreak);
		
		for(int i = 0; i < Data.getPortfolioList().size(); i++) {
			String printOwnerName;
			String printManagerName;
			String printBeneficiaryName;
			
			try {
				printOwnerName = Data.getPortfolioList().get(i).getOwner().printName();
			} catch (NullPointerException e) {
				printOwnerName = "none";
			}
			
			try {
				printManagerName = Data.getPortfolioList().get(i).getManager().printName();
			} catch (NullPointerException e) {
				printManagerName = "none";
			}
			
			try {
				printBeneficiaryName = Data.getPortfolioList().get(i).getBeneficiary().printName();
			} catch (NullPointerException e) {
				printBeneficiaryName = "none";
			}
			
			System.out.printf("Portfolio %s\n", Data.getPortfolioList().get(i).getPortfolioCode());
			System.out.printf("%s\n", singleBreak);
			System.out.printf("Owner:         %s\n", printOwnerName);
			System.out.printf("Manager:       %s\n", printManagerName);
			System.out.printf("Beneficiary:   %s\n", printBeneficiaryName);
			System.out.printf("Asset:\n");
			System.out.printf("%-14s %-40s %10s  %10s    %15s    %13s\n", "CODE", "ASSET", "RETURN RATE", "RISK", "ANNUAL RETURN", "VALUE");
			
			for(int j = 0; j < Data.getPortfolioList().get(i).getAsset().size(); j++) {
				System.out.printf("%-14s %-40.40s %10.2f%%  %10.4f   $%15.2f   $%13.2f\n",
				Data.getPortfolioList().get(i).getAsset().get(j).getAssetCode(),
				Data.getPortfolioList().get(i).getAsset().get(j).getLabel(),
				Data.getPortfolioList().get(i).getAsset().get(j).getReturnRate(),
				Data.getPortfolioList().get(i).getAsset().get(j).getRisk(),
				Data.getPortfolioList().get(i).getAsset().get(j).getAnnualReturn(),
				Data.getPortfolioList().get(i).getAsset().get(j).getActualValue());
			}
			
			System.out.printf("%-14s %-40s %2s %s\n", "", "", "", singleLongBreak);
			
			System.out.printf("%-14s %-40s %10s   %10.4f   $%15.2f   $%13.2f\n", "", "", "TOTALS",
			subRisk(Data.getPortfolioList().get(i)),
			subSumAnnualReturn(Data.getPortfolioList().get(i)),
			subSumValue(Data.getPortfolioList().get(i)));
			
			System.out.printf("\n\n");
		}
	}
	
	public static void printSummary(SortedList<Portfolio> portfolioSortedList) {
		String title = "Portfolio Summary Report";
		String singleLongerBreak = "------------------------------------------------------------------------------------------";
		String doubleBreak = "==============================================================================================================================================================";
		
		System.out.printf("%s\n", title);
		System.out.printf("%s\n", doubleBreak);
		System.out.printf("%-12s %-30s %-30s    %9s    %12s   %14s    %14s    %14s\n",
		"PORTFOLIO", "OWNER", "MANAGER", "FEES", "COMMISSIONS", "WEIGHTED RISK", "RETURN", "TOTAL");
		
		for(int i = 0; i < portfolioSortedList.getSize(); i++) {
			String printOwnerName;
			String printManagerName;
			
			try {
				printOwnerName = portfolioSortedList.get(i).getOwner().printName();
			} catch (NullPointerException e) {
				printOwnerName = "none";
			}
			
			try {
				printManagerName = portfolioSortedList.get(i).getManager().printName();
			} catch (NullPointerException e) {
				printManagerName = "none";
			}
			
			System.out.printf("%-12.12s %-30.30s %-30.30s   $%9.2f   $%12.2f   %14.4f    $%14.2f   $%14.2f\n",
			portfolioSortedList.get(i).getPortfolioCode(),
			printOwnerName,
			printManagerName,
			portfolioSortedList.get(i).getFees(),
			portfolioSortedList.get(i).getCommissions(),
			portfolioSortedList.get(i).getAggregateRiskMeasure(),
			portfolioSortedList.get(i).getPortfolioAnnualReturn(),
			portfolioSortedList.get(i).getPortfolioValue());
		}
		
		System.out.printf("%-12s %-30s %21s %s\n", "", "", "", singleLongerBreak);
		
		System.out.printf("%-12s %-30s %30s   $%9.2f   $%12.2f   %14s    $%14.2f   $%14.2f\n",
		"", "", "TOTALS:",
		sumFees(portfolioSortedList), sumCommissions(portfolioSortedList), "",
		sumAnnualReturn(portfolioSortedList), sumValue(portfolioSortedList));
		
		System.out.printf("\n\n\n\n");
	}
	
	public static void printDetailReport(SortedList<Portfolio> portfolioSortedList) {
		String singleBreak = "--------------------------------------------";
		String singleLongBreak = "--------------------------------------------------------";
		String doubleBreak = "==============================================================================================================================================================";
		String detail = "Portfolio Details:";
		
		System.out.printf("%s\n", detail);
		System.out.printf("%s\n", doubleBreak);
		
		for(int i = 0; i < portfolioSortedList.getSize(); i++) {
			String printOwnerName;
			String printManagerName;
			String printBeneficiaryName;
			
			try {
				printOwnerName = portfolioSortedList.get(i).getOwner().printName();
			} catch (NullPointerException e) {
				printOwnerName = "none";
			}
			
			try {
				printManagerName = portfolioSortedList.get(i).getManager().printName();
			} catch (NullPointerException e) {
				printManagerName = "none";
			}
			
			try {
				printBeneficiaryName = portfolioSortedList.get(i).getBeneficiary().printName();
			} catch (NullPointerException e) {
				printBeneficiaryName = "none";
			}
			
			System.out.printf("Portfolio %s\n", portfolioSortedList.get(i).getPortfolioCode());
			System.out.printf("%s\n", singleBreak);
			System.out.printf("Owner:         %s\n", printOwnerName);
			System.out.printf("Manager:       %s\n", printManagerName);
			System.out.printf("Beneficiary:   %s\n", printBeneficiaryName);
			System.out.printf("Asset:\n");
			System.out.printf("%-14s %-40s %10s  %10s    %15s    %13s\n", "CODE", "ASSET", "RETURN RATE", "RISK", "ANNUAL RETURN", "VALUE");
			
			for(int j = 0; j < portfolioSortedList.get(i).getAsset().size(); j++) {
				System.out.printf("%-14s %-40.40s %10.2f%%  %10.4f   $%15.2f   $%13.2f\n",
				portfolioSortedList.get(i).getAsset().get(j).getAssetCode(),
				portfolioSortedList.get(i).getAsset().get(j).getLabel(),
				portfolioSortedList.get(i).getAsset().get(j).getReturnRate(),
				portfolioSortedList.get(i).getAsset().get(j).getRisk(),
				portfolioSortedList.get(i).getAsset().get(j).getAnnualReturn(),
				portfolioSortedList.get(i).getAsset().get(j).getActualValue());
			}
			
			System.out.printf("%-14s %-40s %2s %s\n", "", "", "", singleLongBreak);
			
			System.out.printf("%-14s %-40s %10s   %10.4f   $%15.2f   $%13.2f\n", "", "", "TOTALS",
			subRisk(portfolioSortedList.get(i)),
			subSumAnnualReturn(portfolioSortedList.get(i)),
			subSumValue(portfolioSortedList.get(i)));
			
			System.out.printf("\n\n");
		}
	}
}
