package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.QuoteManager;
import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.domain.QuoteHistory;
import ba.unsa.etf.rpr.exceptions.QuoteException;


import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Adnan
 * CLI (Command Line Interface) implementation in following class
 * Even though this type of presentation layer (called CLI) is becoming past tense for GUI apps
 * it's good to see how you can manipulate data through command line and database also
 *
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addQuote = new Option("q","add-qoute",false, "Adding new quote to quote-maker database");
    private static final Option addCategory = new Option("c","add-category",false, "Adding new category to quote-maker database");
    private static final Option getQuotes = new Option("getQ", "get-quotes",false, "Printing all quotes from quote-maker database");
    private static final Option getCategories = new Option("getC", "get-categories",false, "Printing all categories from quote-maker database");
    private static final Option categoryDefinition = new Option(null, "category",false, "Defining category for next added quote");




    /**
     *
     * @param options
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar quote-maker.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addQuote);
        options.addOption(addCategory);
        options.addOption(getQuotes);
        options.addOption(getCategories);
        options.addOption(categoryDefinition);
        return options;
    }

    public static Category searchThroughCategories(List<Category> listOfCategories, String categoryName) {

        Category category = null;
        category = listOfCategories.stream().filter(cat -> cat.getName().toLowerCase().equals(categoryName.toLowerCase())).findAny().get();
        return category;

    }


    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

//        while(true) {
        if((cl.hasOption(addQuote.getOpt()) || cl.hasOption(addQuote.getLongOpt())) && cl.hasOption((categoryDefinition.getLongOpt()))){
            QuoteManager quoteManager = new QuoteManager();
            CategoryManager categoryManager = new CategoryManager();
            Category category = null;
            try {
                category = searchThroughCategories(categoryManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no category in the list! Try again.");
                System.exit(1);
            }

//                if(!category.getName().equals(cl.getArgList().get(1))){
//                    System.out.println("There is no category with passed name! Try again.");
//                    System.exit(-1);
//                }
            Quote quote = new Quote();
            quote.setCategory(category);
            quote.setQuote(cl.getArgList().get(0));
            quote.setCreated(Date.valueOf(LocalDate.now()));
            quoteManager.add(quote);
            System.out.println("You successfully added quote to database!");
//                break;
        } else if(cl.hasOption(getQuotes.getOpt()) || cl.hasOption(getQuotes.getLongOpt())){
            QuoteManager quoteManager = new QuoteManager();
            quoteManager.getAll().forEach(q -> System.out.println(q.getQuote()));
//                break;
        } else if(cl.hasOption(addCategory.getOpt()) || cl.hasOption(addCategory.getLongOpt())){
            try {
                CategoryManager categoryManager = new CategoryManager();
                Category cat = new Category();
                cat.setName(cl.getArgList().get(0));
                categoryManager.add(cat);
                System.out.println("Category has been added successfully");
//                    break;
            }catch(Exception e) {
                System.out.println("There is already category with same name in database! Try again");
                System.exit(1);
//                   break;
            }

        } else if(cl.hasOption(getCategories.getOpt()) || cl.hasOption(getCategories.getLongOpt())){
            CategoryManager categoryManager = new CategoryManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getName()));
//                break;
        } else {
            printFormattedOptions(options);
            System.exit(-1);
//                break;
        }
//        }
    }
}