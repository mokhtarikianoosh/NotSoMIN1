
/****************************************************************************************
 * @file  Table.java
 *
 * @author   John Miller
 */

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.Boolean.*;
import static java.lang.System.out;

/****************************************************************************************
 * This class implements relational database tables (including attribute names, domains
 * and a list of tuples.  Five basic relational algebra operators are provided: project,
 * select, union, minus and join. The insert data manipulation operator is also provided.
 * Missing are update and delete data manipulation operators.
 */
public class Table
       implements Serializable
{
    /** Relative path for storage directory
     */
    private static final String DIR = "store" + File.separator;

    /** Filename extension for database files
     */
    private static final String EXT = ".dbf";

    /** Counter for naming temporary tables.
     */
    private static int count = 0;

    /** Table name.
     */
    private final String name;

    /** Array of attribute names.
     */
    private final String [] attribute;

    /** Array of attribute domains: a domain may be
     *  integer types: Long, Integer, Short, Byte
     *  real types: Double, Float
     *  string types: Character, String
     */
    private final Class [] domain;

    /** Collection of tuples (data storage).
     */
    private final List <Comparable []> tuples;

    /** Primary key. 
     */
    private final String [] key;

    /** Index into tuples (maps key to tuple number).
     */
    private final Map <KeyType, Comparable []> index;

    //----------------------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------------------

    /************************************************************************************
     * Construct an empty table from the meta-data specifications.
     *
     * @param _name       the name of the relation
     * @param _attribute  the string containing attributes names
     * @param _domain     the string containing attribute domains (data types)
     * @param _key        the primary key
     */  
    public Table (String _name, String [] _attribute, Class [] _domain, String [] _key)
    {
        name      = _name;
        attribute = _attribute;
        domain    = _domain;
        key       = _key;
        tuples    = new ArrayList <> ();
        index     = new TreeMap <> ();       // also try BPTreeMap, LinHashMap or ExtHashMap
      //  index     = new LinHashMap <> (KeyType.class, Comparable [].class);

    } // constructor

    /************************************************************************************
     * Construct a table from the meta-data specifications and data in _tuples list.
     *
     * @param _name       the name of the relation
     * @param _attribute  the string containing attributes names
     * @param _domain     the string containing attribute domains (data types)
     * @param _key        the primary key
     * @param _tuple      the list of tuples containing the data
     */  
    public Table (String _name, String [] _attribute, Class [] _domain, String [] _key,
                  List <Comparable []> _tuples)
    {
        name      = _name;
        attribute = _attribute;
        domain    = _domain;
        key       = _key;
        tuples    = _tuples;
        index     = new TreeMap <> ();       // also try BPTreeMap, LinHashMap or ExtHashMap
    } // constructor

    /************************************************************************************
     * Construct an empty table from the raw string specifications.
     *
     * @param name        the name of the relation
     * @param attributes  the string containing attributes names
     * @param domains     the string containing attribute domains (data types)
     */
    public Table (String name, String attributes, String domains, String _key)
    {
        this (name, attributes.split (" "), findClass (domains.split (" ")), _key.split(" "));

        out.println ("DDL> create table " + name + " (" + attributes + ")");
    } // constructor

    //----------------------------------------------------------------------------------
    // Public Methods
    //----------------------------------------------------------------------------------

    /************************************************************************************
     * Project the tuples onto a lower dimension by keeping only the given attributes.
     * Check whether the original key is included in the projection.
     *
     * #usage movie.project ("title year studioNo")
     * @author Rachel Lilja
     * @param attributes  the attributes to project onto
     * @return  a table of projected tuples
     */
    public Table project (String attributes)
    {
        out.println ("RA> " + name + ".project (" + attributes + ")");
        String [] attrs     = attributes.split (" ");
        Class []  colDomain = extractDom (match (attrs), domain);
        String [] newKey    = (Arrays.asList (attrs).containsAll (Arrays.asList (key))) ? key : attrs;
        
        List <Comparable []> rows = new ArrayList <> ();
        
        //iterates through the tuples in the table
        for(int i = 0; i < tuples.size(); i++)
        {
        		//creates an array of counts for the target attribute in the tuple
        		int count [] = new int[tuples.size()];
        		//sets all values in the array to defaulted 0
        		for(int k = 0; k < tuples.size(); k++ )
        		{
        			count[k] = 0;
        		}//for
        		
        		//iterates through the tuples in the table to find duplicates
        		for(int j = i+1; j < tuples.size(); j++)
        		{
        			//compares the target attribute in the tuple to the next target attribute in the
        			//tuple after
        			if(compareComparable(extract(tuples.get(i),attrs), extract(tuples.get(j),attrs)))
        			{
        				//adds a one to the count if a duplicate is found
        				count[i] += 1;
        			}//if
        		}//for
        		
        		//only adds the target attribute to the table if it doesn't have a duplicate
        		if(count[i] == 0)
        		{
        			rows.add(extract(tuples.get(i),attrs));
        		}//if
        }//for
         
        //returns to the user the final table
        return new Table (name + count++, attrs, colDomain, newKey, rows);
    } // project

    /************************************************************************************
     * Select the tuples satisfying the given predicate (Boolean function).
     *
     * #usage movie.select (t -> t[movie.col("year")].equals (1977))
     *
     * @param predicate  the check condition for tuples
     * @return  a table with tuples satisfying the predicate
     */
    public Table select (Predicate <Comparable []> predicate)
    {
        out.println ("RA> " + name + ".select (" + predicate + ")");

        return new Table (name + count++, attribute, domain, key,
                   tuples.stream ().filter (t -> predicate.test (t))
                                   .collect (Collectors.toList ()));
    } // select

    /************************************************************************************
     * Select the tuples satisfying the given key predicate (key = value).  Use an index
     * (Map) to retrieve the tuple with the given key value.
     *
     * @author Jose Casseres
     * @param keyVal  the given key value
     * @return  a table with the tuple satisfying the key predicate
     */
    public Table select (KeyType keyVal)
    {
        out.println ("RA> " + name + ".select (" + keyVal + ")");

        List <Comparable []> rows = new ArrayList <> ();

        //if the index key matches the target key, add that attribute to the table
        if(index.containsKey(keyVal))
        	rows.add(index.get(keyVal));
        		
        
        return new Table (name + count++, attribute, domain, key, rows);
    } // select

    /************************************************************************************
     * Union this table and table2.  Check that the two tables are compatible.
     *
     * #usage movie.union (show)
     *
     * @author Kianoosh Mokhtari(2017)
     * @param table2  the rhs table in the union operation
     * @return  a table representing the union
     */
    public Table union (Table table2)
    {
        out.println ("RA> " + name + ".union (" + table2.name + ")");
        
        List <Comparable []> rows = new ArrayList <> ();
        
        if (! compatible (table2)) 
        {
        	return new Table (name + count++, attribute, domain, key, rows);
        	//return null;
        }

        //populates the rows arrayList with lhs and rhs data
        for(Comparable [] e : tuples)
        	rows.add(e);
        
        for(Comparable [] e : table2.tuples)
        	rows.add(e);
        //  T O   B E   I M P L E M E N T E D 
        
        //compares rows to rows+1 to see if there are duplicates
        for(int i = 0; i < rows.size(); i++)
        {
        	for(int j = i+1; j < rows.size(); j++)
        	{
        		if(compareComparable(rows.get(i), rows.get(j)))
        		{
        			rows.remove(j);
        		}
        	}
        }
        
        return new Table (name + count++, attribute, domain, key, rows);
    } // union

    /************************************************************************************
     * Take the difference of this table and table2.  Check that the two tables are
     * compatible.
     *
     * #usage movie.minus (show)
     *
     * @author Rachel Lilja
     * @param table2  The rhs table in the minus operation
     * @return  a table representing the difference
     */
    public Table minus (Table table2)
    {
        out.println ("RA> " + name + ".minus (" + table2.name + ")");

        List <Comparable []> rows = new ArrayList <> ();
        
        //checks if the lhs is compatible with the rhs table
        
        if (compatible(table2) == false) 
        {
        	return new Table (name + count++, attribute, domain, key, rows);
        	//return null;
        }//if
        
        //populates the rows arrayList with elements from the original tuple arrayList
        for(Comparable [] e : tuples)
        {
        	rows.add(e);
        }//for

        //iterates through the elements in rows and compares them to elements in table2
        for(int i = 0; i < table2.tuples.size(); i++)
        {
        	for(int j = 0; j < rows.size(); j++)
        	{
        		//if an element from rows equals an element from table2, remove it from rows
        		if(compareComparable(table2.tuples.get(i), rows.get(j)))
        		{
        			rows.remove(j);
        		}//if
        	}//for
        }//for
        
        //returns to the user the final table
        return new Table (name + count++, attribute, domain, key, rows);
    } // minus

    /************************************************************************************
     * Join this table and table2 by performing an "equi-join".  Tuples from both tables
     * are compared requiring attributes1 to equal attributes2.  Disambiguate attribute
     * names by append "2" to the end of any duplicate attribute name.
     *
     * #usage movie.join ("studioNo", "name", studio)
     *
     * @author Kianoosh Mokhtari
     * @param attribute1  the attributes of this table to be compared (Foreign Key)
     * @param attribute2  the attributes of table2 to be compared (Primary Key)
     * @param table2      the rhs table in the join operation
     * @return  a table with tuples satisfying the equality predicate
     */
    public Table join (String attributes1, String attributes2, Table table2)
    {
        out.println ("RA> " + name + ".join (" + attributes1 + ", " + attributes2 + ", "
                                               + table2.name + ")");

        String [] t_attrs = attributes1.split (" ");
        String [] u_attrs = attributes2.split (" ");
        
        List <Comparable []> lhs = new ArrayList<>();
        List<KeyType> foreignKeys = new ArrayList<>();
        		
        for(Comparable[] e : tuples)
        	lhs.add(extract(e, t_attrs));
        
        for(Comparable[] t : lhs)
        	foreignKeys.add(new KeyType(t));

       
        List<String> clean = new ArrayList<>();
       	for(String w : attribute){
       		for(String a : t_attrs)
       		{
       			if(!w.equalsIgnoreCase(a)){
       				clean.add(w);
       			}
       		}
       	}
       	
       	String[] helpClean = new String[clean.size()];
       	for(int i =0 ; i < clean.size(); i++){
       		helpClean[i] = clean.get(i);
       	}
        	
        
        List <Comparable []> rows = new ArrayList <> ();
      
       	 Comparable [] temp = null;
       	for(int j = 0; j  < foreignKeys.size() ; j++){
       		if(table2.index.containsKey(foreignKeys.get(j))){
       			temp = ArrayUtil.concat(extract(tuples.get(j),helpClean), table2.index.get(foreignKeys.get(j)));
       			rows.add(temp);
       		}
       	}
       	
       	
    
        //  T O   B E   I M P L E M E N T E D 
       	List<String> cleanUp = new ArrayList<>();
       	for(String x : attribute){
       		for(String y : t_attrs)
       		{
       			if(!x.equalsIgnoreCase(y)){
       				cleanUp.add(x);
       			}
       		}
       	}
       	
       	String[] helper = new String[cleanUp.size()];
       	for(int i =0 ; i < cleanUp.size(); i++){
       		helper[i] = cleanUp.get(i);
       	}
       	
       	Class[] helpDomain = extractDom (match (helper), domain);
    	
    	

       	return new Table (name + count++, ArrayUtil.concat (helper, table2.attribute),
                ArrayUtil.concat (helpDomain, table2.domain), key, rows);
       	
       	
       
        /*return new Table (name + count++, ArrayUtil.concat (attribute, table2.attribute),
                                          ArrayUtil.concat (domain, table2.domain), key, rows);*/
    } // join

    /************************************************************************************
     * Join this table and table2 by performing an "natural join".  Tuples from both tables
     * are compared requiring common attributes to be equal.  The duplicate column is also
     * eliminated.
     *
     * #usage movieStar.join (starsIn)
     * 
     * @author Jose Casseres
     * @param table2  the rhs table in the join operation
     * @return  a table with tuples satisfying the equality predicate
     */
    public Table join (Table table2)
    {
        out.println ("RA> " + name + ".join (" + table2.name + ")");

        List <Comparable []> rows = new ArrayList <> ();
        String[] finalAttrs = null;
        Class[] cleanDomain = null;
        //  T O   B E   I M P L E M E N T E D 
       
   
        List<String> table1Attrs = new ArrayList<>();
        //finalAttrs = new String[attribute.length + table2.attribute.length];
        String[] rhsAttrs;
        String[] lhsAttrs = attribute;
        List<String> toCompare = new ArrayList<>();
        List<String> keep = new ArrayList<>(); // know what rhs attributes are unique
        //List<String> table2Attrs = new ArrayList<>();
        
        for(String e : attribute)
        	table1Attrs.add(e);
        	
        
        
        
        for(int j =0 ; j < table2.attribute.length; j++){
        	if(!table1Attrs.contains(table2.attribute[j])){
        		table1Attrs.add(table2.attribute[j]);
        		keep.add(table2.attribute[j]);
        	}else{
        		toCompare.add(table2.attribute[j]);
        	}
        }
        
        
        
        
        String[] comparison = new String[toCompare.size()];
        for(int t = 0 ; t < toCompare.size(); t++)
        	comparison[t] = toCompare.get(t);
        	
        rhsAttrs = new String[keep.size()];
        for(int i =0; i < keep.size(); i++)
        	rhsAttrs[i] = keep.get(i);        	
        
        finalAttrs = new String[table1Attrs.size()];
        for(int i =0; i < table1Attrs.size(); i++)
        	finalAttrs[i] = table1Attrs.get(i);
        
        if(keep.size() > 0){
	        cleanDomain = ArrayUtil.concat(extractDom(match(lhsAttrs),domain),table2.extractDom(table2.match(rhsAttrs),table2.domain));
	        	
	        Comparable [] tuple1 = null;
	        Comparable [] tuple2 = null;
	        	for(int x = 0; x < tuples.size(); x ++){
	        		tuple1 = extract(tuples.get(x),comparison);
	        		for(int y = 0; y < table2.tuples.size(); y++){
	        			tuple2 = extract(table2.tuples.get(y), comparison);
	        			if(compareComparable(tuple1,tuple2)){
	        				Comparable [] temp = ArrayUtil.concat(extract(tuples.get(x),lhsAttrs), table2.extract(table2.tuples.get(y),rhsAttrs));
	        				rows.add(temp);
	        			}
	        		}
	        		
	        	}
	        
	        }
        else{
        	
        	 cleanDomain = extractDom(match(lhsAttrs),domain);
	  
 	       Comparable[] tuple1 =null;
 	      Comparable [] tuple2 =null;
 	        	for(int x = 0; x < tuples.size(); x ++){
 	        		tuple1 = extract(tuples.get(x),comparison);
 	        		for(int y = 0; y < table2.tuples.size(); y++){
 	        			tuple2 = extract(table2.tuples.get(y), comparison);	
 	        			if(compareComparable(tuple1,tuple2)){
 	        				Comparable [] temp = extract(tuples.get(x),lhsAttrs);
 	        				rows.add(temp);
 	        			}
 	        		}
 	        		
 	        	}
        	
        }
        

        // FIX - eliminate duplicate columns
        return new Table (name + count++, finalAttrs,cleanDomain, key, rows);
    } // join

    /************************************************************************************
     * Return the column position for the given attribute name.
     *
     * @param attr  the given attribute name
     * @return  a column position
     */
    public int col (String attr)
    {
        for (int i = 0; i < attribute.length; i++) {
           if (attr.equals (attribute [i])) return i;
        } // for

        return -1;  // not found
    } // col

    /************************************************************************************
     * Insert a tuple to the table.
     *
     * #usage movie.insert ("'Star_Wars'", 1977, 124, "T", "Fox", 12345)
     *
     * @param tup  the array of attribute values forming the tuple
     * @return  whether insertion was successful
     */
    public boolean insert (Comparable [] tup)
    {
        out.println ("DML> insert into " + name + " values ( " + Arrays.toString (tup) + " )");

        if (typeCheck (tup)) {
            tuples.add (tup);
            Comparable [] keyVal = new Comparable [key.length];
            int []        cols   = match (key);
            for (int j = 0; j < keyVal.length; j++) keyVal [j] = tup [cols [j]];
            index.put (new KeyType (keyVal), tup);
            return true;
        } else {
            return false;
        } // if
    } // insert

    /************************************************************************************
     * Get the name of the table.
     *
     * @return  the table's name
     */
    public String getName ()
    {
        return name;
    } // getName

    /************************************************************************************
     * Print this table.
     */
    public void print ()
    {
        out.println ("\n Table " + name);
        out.print ("|-");
        for (int i = 0; i < attribute.length; i++) out.print ("---------------");
        out.println ("-|");
        out.print ("| ");
        for (String a : attribute) out.printf ("%15s", a);
        out.println (" |");
        out.print ("|-");
        for (int i = 0; i < attribute.length; i++) out.print ("---------------");
        out.println ("-|");
        for (Comparable [] tup : tuples) {
            out.print ("| ");
            for (Comparable attr : tup) out.printf ("%15s", attr);
            out.println (" |");
        } // for
        out.print ("|-");
        for (int i = 0; i < attribute.length; i++) out.print ("---------------");
        out.println ("-|");
    } // print

    /************************************************************************************
     * Print this table's index (Map).
     */
    public void printIndex ()
    {
        out.println ("\n Index for " + name);
        out.println ("-------------------");
        for (Map.Entry <KeyType, Comparable []> e : index.entrySet ()) {
            out.println (e.getKey () + " -> " + Arrays.toString (e.getValue ()));
        } // for
        out.println ("-------------------");
    } // printIndex

    /************************************************************************************
     * Load the table with the given name into memory. 
     *
     * @param name  the name of the table to load
     */
    public static Table load (String name)
    {
        Table tab = null;
        try {
            ObjectInputStream ois = new ObjectInputStream (new FileInputStream (DIR + name + EXT));
            tab = (Table) ois.readObject ();
            ois.close ();
        } catch (IOException ex) {
            out.println ("load: IO Exception");
            ex.printStackTrace ();
        } catch (ClassNotFoundException ex) {
            out.println ("load: Class Not Found Exception");
            ex.printStackTrace ();
        } // try
        return tab;
    } // load

    /************************************************************************************
     * Save this table in a file.
     */
    public void save ()
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (DIR + name + EXT));
            oos.writeObject (this);
            oos.close ();
        } catch (IOException ex) {
            out.println ("save: IO Exception");
            ex.printStackTrace ();
        } // try
    } // save

    //----------------------------------------------------------------------------------
    // Private Methods
    //----------------------------------------------------------------------------------

    /************************************************************************************
     * Determine whether the two tables (this and table2) are compatible, i.e., have
     * the same number of attributes each with the same corresponding domain.
     *
     * @param table2  the rhs table
     * @return  whether the two tables are compatible
     */
    private boolean compatible (Table table2)
    {
        if (domain.length != table2.domain.length) {
            out.println ("compatible ERROR: table have different arity");
            return false;
        } // if
        for (int j = 0; j < domain.length; j++) {
            if (domain [j] != table2.domain [j]) {
                out.println ("compatible ERROR: tables disagree on domain " + j);
                return false;
            } // if
        } // for
        return true;
    } // compatible

    /************************************************************************************
     * Match the column and attribute names to determine the domains.
     *
     * @param column  the array of column names
     * @return  an array of column index positions
     */
    private int [] match (String [] column)
    {
        int [] colPos = new int [column.length];

        for (int j = 0; j < column.length; j++) {
            boolean matched = false;
            for (int k = 0; k < attribute.length; k++) {
                if (column [j].equals(attribute [k])) { //ask about possible change to equalsIgnoreCase
                    matched = true;
                    colPos [j] = k;
                } // for
            } // for
            if ( ! matched) {
                out.println ("match: domain not found for " + column [j]);
            } // if
        } // for

        return colPos;
    } // match

    /************************************************************************************
     * Extract the attributes specified by the column array from tuple t.
     *
     * @param t       the tuple to extract from
     * @param column  the array of column names
     * @return  a smaller tuple extracted from tuple t 
     */
    private Comparable [] extract (Comparable [] t, String [] column)
    {
        Comparable [] tup = new Comparable [column.length];
        int [] colPos = match (column);
        for (int j = 0; j < column.length; j++) tup [j] = t [colPos [j]];
        return tup;
    } // extract

    /************************************************************************************
     * Check the size of the tuple (number of elements in list) as well as the type of
     * each value to ensure it is from the right domain. 
     *
     * @author Jose Casseres
     * @param t  the tuple as a list of attribute values
     * @return  whether the tuple has the right size and values that comply
     *          with the given domains
     */
    private boolean typeCheck ( Comparable [] t)
    { 
    //  T O   B E   I M P L E M E N T E D 
    	if(attribute.length == t.length){
    		for(int i = 0; i < attribute.length ; i++ ){
    			if(!domain[i].getName().equalsIgnoreCase(t[i].getClass().getName())){
    				out.println("false because of object difference");
    				return false;
    			}
    		}
    		return true;
    	}
    	else{
    		out.println("false because of size");
    		return false;
    	}
    	
    	
    	//return true;
    	
    	
    } // typeCheck

    /**
     * helper function
     * 
     * 
     */
    private boolean compareComparable(Comparable [] x, Comparable [] t){
	    
		if(x.length == t.length)
		{
			for(int i = 0; i < x.length; i++)
			{
				if(!x[i].equals(t[i]))
					return false;
			}
			return true;
		}
		else
			return false;
    }
    
    
    /************************************************************************************
     * Find the classes in the "java.lang" package with given names.
     *
     * @param className  the array of class name (e.g., {"Integer", "String"})
     * @return  an array of Java classes
     */
    private static Class [] findClass (String [] className)
    {
        Class [] classArray = new Class [className.length];

        for (int i = 0; i < className.length; i++) {
            try {
                classArray [i] = Class.forName ("java.lang." + className [i]);
            } catch (ClassNotFoundException ex) {
                out.println ("findClass: " + ex);
            } // try
        } // for

        return classArray;
    } // findClass

    /************************************************************************************
     * Extract the corresponding domains.
     *
     * @param colPos the column positions to extract.
     * @param group  where to extract from
     * @return  the extracted domains
     */
    private Class [] extractDom (int [] colPos, Class [] group)
    {
        Class [] obj = new Class [colPos.length];

        for (int j = 0; j < colPos.length; j++) {
            obj [j] = group [colPos [j]];
        } // for

        return obj;
    } // extractDom

} // Table class

