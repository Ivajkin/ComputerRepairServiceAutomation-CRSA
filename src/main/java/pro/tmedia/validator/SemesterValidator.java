package pro.tmedia.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pro.tmedia.model.Semester;

/**
 * User: Ivaykin Timofey
 * Date: 2/20/14
 */
public class SemesterValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return Semester.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Semester sem = (Semester)target;

        if("NONE".equals(sem.getName())){
            errors.rejectValue("sem", "required.sem");
        }
    }
}