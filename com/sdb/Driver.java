package com.sdb;

import java.util.Comparator;

/**
 * Driver.java
 * Brooke Lampe
 * 2017/02/02
 * This class, given a database, will store its contents in objects, and then print the objects in XML and JSON file formats
 * along with a summary report sent to the standard output
 */

public class Driver {

	public static void main(String[] args){
		
		//Load pertinent data from the database
		LoadAsset.getAsset();
		LoadPerson.getPerson();
		LoadPortfolio.getPortfolio();
		
		//Create sorted list ordered by owner name
		Node<Portfolio> head = null;
		Comparator<Portfolio> comparator = Portfolio.byOwner;
		
		SortedList<Portfolio> portfolioSortedList = new SortedList<Portfolio>(head, comparator);
		portfolioSortedList.insert(Data.getPortfolioList());
		
		//Print summary ordered by owner name
		Summary.printSummary(portfolioSortedList);
		
		//Create sorted list ordered by portfolio total value
		head = null;
		comparator = Portfolio.byValueHighestToLowest;
		
		portfolioSortedList = new SortedList<Portfolio>(head, comparator);
		portfolioSortedList.insert(Data.getPortfolioList());
		
		//Print summary ordered by portfolio total value
		Summary.printSummary(portfolioSortedList);
		
		//Create sorted list ordered by broker type and then by broker name
		head = null;
		comparator = Portfolio.byBroker;
		
		portfolioSortedList = new SortedList<Portfolio>(head, comparator);
		portfolioSortedList.insert(Data.getPortfolioList());
		
		//Print summary ordered by broker type and then by broker name
		Summary.printSummary(portfolioSortedList);
	}
}