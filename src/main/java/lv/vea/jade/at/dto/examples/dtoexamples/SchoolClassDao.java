package lv.vea.jade.at.dto.examples.dtoexamples;

import lv.vea.jade.at.dto.examples.dtoexamples.dto.SchoolClass;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassDao {

    public SchoolClass findClassByYear(final String classYear) {
        return new SchoolClass();
    }

    public void saveClass(final SchoolClass schoolClass) {
        return;
    }
}
