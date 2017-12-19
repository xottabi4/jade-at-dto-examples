package lv.vea.jade.at.dto.examples.dtoexamples;

import lv.vea.jade.at.dto.examples.dtoexamples.dto.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/graduatedClass")
public class SchoolController {

    private SchoolClassDao schoolClassDao;

    @Autowired
    public SchoolController(final SchoolClassDao schoolClassDao) {
        this.schoolClassDao = schoolClassDao;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addGraduatedClass(@RequestBody SchoolClass schoolClass) {
        schoolClassDao.saveClass(schoolClass);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public SchoolClass getGraduatedClass(@RequestParam("classYear") String classYear) {
        return schoolClassDao.findClassByYear(classYear);
    }
}
