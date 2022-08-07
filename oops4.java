public class MyClass {
    public static void main(String args[]) throws CloneNotSupportedException{
      a obj1 = new a(10);
     // System.out.println(obj1.num);
     
      // object class
      
      System.out.println(obj1); // It will print toString method of object class will getclass name getname  and hashcode of the object(unique).
      // output : a@58d25a40 (a is class name and value after @ is unique hashcode for object)
    
        System.out.println(obj1.toString());
        System.out.println(obj1.hashCode());
        
        // equals method of object class (check for reference not for value)
        a obj2 = new a(20);
        a obj3 = new a(20);
        
        System.out.println(obj2.equals(obj3)); //false
        a obj4 = obj3;
        System.out.println(obj3.equals(obj4)); //true
        
        // shallow copy (copies only properties)
        a obj5 = obj3;
        
        // previously obj3.num was 20 ;
        obj5.num = 50;
        // now obj3.num will also gets 50 as obj5 refering to the same object as obj3 in heap memory
        System.out.println(obj3.num);
        
        
        
        
        // finalize method will call when the garbage collection happens.
         a test = new a(20);
         test = null;
         System.gc(); // calling garbage collector
        
    }
    
    @Override protected void finalize()
    {
        System.out.println("finalize method called");
    }
}


class a implements {
     int num;
     
     a(int a){
         num = a;
     }
   
    @Override
    public String toString()
{
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}

     @Override public int hashCode() { return num; }
     
     
}
/*

Access Control:

How a member can be accessed is determined by the access modifier attached to its declaration.
Usually, you will want to restrict access to the data members of a class—allowing access only through methods.
Also, there will be times when you will want to define methods that are private to a class.

Java’s access modifiers are public, private, and protected. Java also defines a default access level.
protected applies only when inheritance is involved.

When no access modifier is used, then by default the member of a class is public within its own package,
but cannot be accessed outside of its package.

            │ Class │ Package │ Subclass │ Subclass │ World
            │       │         │(same pkg)│(diff pkg)│(diff pkg & not subclass)
────────────┼───────┼─────────┼──────────┼──────────┼──────────────────────────
public      │   +   │    +    │    +     │     +    │   +
────────────┼───────┼─────────┼──────────┼──────────┼──────────────────────────
protected   │   +   │    +    │    +     │     +    │
────────────┼───────┼─────────┼──────────┼──────────┼──────────────────────────
no modifier │   +   │    +    │    +     │          │
────────────┼───────┼─────────┼──────────┼──────────┼──────────────────────────
private     │   +   │         │          │          │

+ : accessible
blank : not accessible

package packageOne;
public class Base
{
    protected void display(){
        System.out.println("in Base");
    }
}

package packageTwo;
public class Derived extends packageOne.Base{
    public void show(){
        new Base().display();       // this is not working
        new Derived().display();    // is working
        display();//is working
    }
}

protected allows access from subclasses and from other classes in the same package.
We can use child class to use protected member outside the package but only child class object can access it.
That's why any Derived class instance can access the protected method in Base.
The other line creates a Base instance (not a Derived instance!!).
And access to protected methods of that instance is only allowed from objects of the same package.

display();
-> allowed, because the caller, an instance of Derived has access to protected members and fields of its subclasses,
even if they're in different packages


new Derived().display();
-> allowed, because you call the method on an instance of Derived and that instance has access to the protected methods
of its subclasses

new Base().display();
-> not allowed because the caller's (the this instance) class is not defined in the same package like the Base class,
so this can't access the protected method. And it doesn't matter - as we see - that the current subclasses a class from
that package. That backdoor is closed ;)

Remember that any time talks about a subclass having an access to a superclass member, we could be talking about the
subclass inheriting the member, not simple accessing the member through a reference to an instance of the superclass.


class C
    protected member;

// in a different package

class S extends C

    obj.member; // only allowed if type of obj is S or subclass of S

The motivation is probably as following. If obj is an S, class S has sufficient knowledge of its internals,
it has the right to manipulate its members, and it can do this safely.
If obj is not an S, it's probably another subclass S2 of C, which S has no idea of.
S2 may have not even been born when S is written. For S to manipulate S2's protected internals is quite dangerous.
If this is allowed, from S2's point of view, it doesn't know who will tamper with its protected internals and how,
this makes S2 job very hard to reason about its own state.

Now if obj is D, and D extends S, is it dangerous for S to access obj.member? Not really.
How S uses member is a shared knowledge of S and all its subclasses, including D. S as the superclass has the right to
define behaviours, and D as the subclass has the obligation to accept and conform.

For easier understanding, the rule should really be simplified to require obj's (static) type to be exactly S.
After all, it's very unusual and inappropriate for subclass D to appear in S. And even if it happens,
that the static type of obj is D, our simplified rule can deal with it easily by upcasting: ((S)obj).member


*/


/*

BUILT IN PACKAGES IN JAVA

java.sql: Provides the classes for accessing and processing data stored in a database. Classes like Connection, DriverManager, PreparedStatement, ResultSet, Statement, etc. are part of this package.
java.lang: Contains classes and interfaces that are fundamental to the design of the Java programming language. Classes like String, StringBuffer, System, Math, Integer, etc. are part of this package.
java.util: Contains the collections framework, some internationalization support classes, properties, random number generation classes. Classes like ArrayList, LinkedList, HashMap, Calendar, Date, Time Zone, etc. are part of this package.
java.net: Provides classes for implementing networking applications. Classes like Authenticator, HTTP Cookie, Socket, URL, URLConnection, URLEncoder, URLDecoder, etc. are part of this package.
java.io: Provides classes for system input/output operations. Classes like BufferedReader, BufferedWriter, File, InputStream, OutputStream, PrintStream, Serializable, etc. are part of this package.
java.awt: Contains classes for creating user interfaces and for painting graphics and images. Classes like Button, Color, Event, Font, Graphics, Image, etc. are part of this package.
*/


/*

OBJECT CLASS IN JAVA

Object class is present in java.lang package. 
Every class in Java is directly or indirectly derived from the Object class. 
If a class does not extend any other class then it is a direct child class of Object and if extends another class then it is indirectly derived. Therefore the Object class methods are available to all Java classes. Hence Object class acts as a root of inheritance hierarchy in any Java Program.

Using Object Class Methods
There are methods in the Object class: 

1. toString(): The toString() provides a String representation of an object and is used to convert an object 
to String. The default toString() method for class Object returns a string consisting of the name of the 
class of which the object is an instance, the at-sign character `@’, and the unsigned hexadecimal 
representation of the hash code of the object. In other words, it is defined as:

// Default behavior of toString() is to print class name, then
// @, then unsigned hexadecimal representation of the hash code
// of the object

public String toString()
{
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
It is always recommended to override the toString() method to get our own String representation of Object. 
For more on override of toString() method refer – Overriding toString() in Java 

Note: Whenever we try to print any Object reference, then internally toString() method is called.

Student s = new Student();

// Below two statements are equivalent
System.out.println(s);
System.out.println(s.toString());


//2. hashCode(): For every object, JVM generates a unique number which is hashcode. It returns distinct integers for distinct objects. A common misconception about this method is that the hashCode() method returns the address of the object, which is not correct. It converts the internal address of the object to an integer by using an algorithm. The hashCode() method is native because in Java it is impossible to find the address of an object, so it uses native languages like C/C++ to find the address of the object.

Use of hashCode() method: It returns a hash value that is used to search objects in a collection. JVM(Java Virtual Machine) uses the hashcode method while saving objects into hashing-related data structures like HashSet, HashMap, Hashtable, etc. The main advantage of saving objects based on hash code is that searching becomes easy. 

Note: Override of hashCode() method needs to be done such that for every object we generate a unique number. For example, for a Student class, we can return the roll no. of a student from the hashCode() method as it is unique. 

// Java program to demonstrate working of
// hashCode() and toString()
 
public class Student {
    static int last_roll = 100;
    int roll_no;
 
    // Constructor
    Student()
    {
        roll_no = last_roll;
        last_roll++;
    }
 
    // Overriding hashCode()
    @Override public int hashCode() { return roll_no; }
 
    // Driver code
    public static void main(String args[])
    {
        Student s = new Student();
 
        // Below two statements are equivalent
        System.out.println(s);
        System.out.println(s.toString());
    }
}
Output :

Student@64
Student@64
Note that 4*160 + 6*161 = 100 

3. equals(Object obj): It compares the given object to “this” object (the object on which the method is called). It gives a generic way to compare objects for equality. It is recommended to override the equals(Object obj) method to get our own equality condition on Objects. For more on override of equals(Object obj) method refer – Overriding equals method in Java

Note: It is generally necessary to override the hashCode() method whenever this method is overridden, so as to maintain the general contract for the hashCode method, which states that equal objects must have equal hash codes. 

4. getClass(): It returns the class object of “this” object and is used to get the actual runtime class of the object. It can also be used to get metadata of this class. The returned Class object is the object that is locked by static synchronized methods of the represented class. As it is final so we don’t override it.

// Java program to demonstrate working of getClass()
 
public class Test {
    public static void main(String[] args)
    {
        Object obj = new String("GeeksForGeeks");
        Class c = obj.getClass();
        System.out.println("Class of Object obj is : "
                           + c.getName());
    }
}
Output: 

Class of Object obj is : java.lang.String
Note: After loading a .class file, JVM will create an object of the type java.lang.Class in the Heap area. We can use this class object to get Class level information. It is widely used in Reflection 

5. finalize() method: This method is called just before an object is garbage collected. It is called the Garbage Collector on an object when the garbage collector determines that there are no more references to the object. We should override finalize() method to dispose of system resources, perform clean-up activities and minimize memory leaks. For example, before destroying Servlet objects web container, always called finalize method to perform clean-up activities of the session. 

Note: The finalize method is called just once on an object even though that object is eligible for garbage collection multiple times. 

// Java program to demonstrate working of finalize()
 
public class Test {
    public static void main(String[] args)
    {
        Test t = new Test();
        System.out.println(t.hashCode());
 
        t = null;
 
        // calling garbage collector
        System.gc();
 
        System.out.println("end");
    }
 
    @Override protected void finalize()
    {
        System.out.println("finalize method called");
    }
}
Output: 

366712642
finalize method called
end
6. clone(): It returns a new object that is exactly the same as this object. For clone() method refer Clone().

The remaining three methods wa
*/
