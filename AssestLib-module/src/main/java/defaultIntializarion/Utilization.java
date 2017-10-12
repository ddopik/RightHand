package defaultIntializarion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ddopik on 10/11/2017.
 */

//Because T is not a value -
// ---->it's just a hint for the compiler. The JVM has no clue of the T.
// You can use generics only as a type for the purposes of type checking at compile tim

public class Utilization {


    ///getting instance of the giving String
//    Note that you need to provide the fully qualified name of the class for the class loader to find it.
//    I.e., if 'class' is actually in some package, you need to do forName("your.package.A") for it to work.
    public Class<?> getClassInstance(String className) throws ClassNotFoundException {

        return Class.forName(className);
    }

    public String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
}
