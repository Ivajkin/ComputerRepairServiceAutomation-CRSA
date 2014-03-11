package pro.tmedia.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: Ivaykin Timofey
 * Date: 2/20/14
 */
public class SemesterValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return true;//Semester1.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        /*Semester1 sem = (Semester1)target;

        if("NONE".equals(sem.getName())){
            errors.rejectValue("sem", "required.sem");
        }     */
    }
}