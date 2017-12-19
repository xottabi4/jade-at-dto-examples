package lv.vea.jade.at.dto.examples.dtoexamples;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lv.vea.jade.at.dto.examples.dtoexamples.dto.SchoolClass;
import lv.vea.jade.at.dto.examples.dtoexamples.dto.Student;
import lv.vea.jade.at.dto.examples.dtoexamples.dto.Teacher;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolControllerTest {

    @Mock
    private SchoolClassDao schoolClassDao;

    @InjectMocks
    private SchoolController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public static void init() throws Exception {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreComments(true);
    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetGraduatedClass() throws Exception {
        final String classYear = "111";

        when(schoolClassDao.findClassByYear(anyString()))
                .thenReturn(createTestClass(classYear));

        final MvcResult result = mockMvc.perform(
                get("/graduatedClass").param("classYear", classYear)
                        .accept(MediaType.TEXT_XML))
                .andExpect(status().isOk())
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        final String expectedXml = new String(readResourceFileAsBytes("testdata/graduatedClass.xml"), StandardCharsets.UTF_8);
        XMLAssert.assertXMLEqual(expectedXml, content);
    }

    @Test
    public void testAddGraduatedClass() throws Exception {
        mockMvc.perform(
                post("/graduatedClass")
                        .contentType(MediaType.TEXT_XML)
                        .content(readResourceFileAsBytes("testdata/graduatedClass.xml")))
                .andExpect(status().isCreated());

        verify(schoolClassDao, times(1)).saveClass(any());
        verifyNoMoreInteractions(schoolClassDao);
    }

    private byte[] readResourceFileAsBytes(final String fileName) throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
        return Files.readAllBytes(path);
    }

    private SchoolClass createTestClass(final String classYear) {
        final SchoolClass schoolClass = new SchoolClass();
        schoolClass.getStudents().add(new Student("name1", "surname1"));
        schoolClass.getStudents().add(new Student("name2", "surname2"));
        schoolClass.setTeacher(new Teacher("teachername", "teachersurname"));
        schoolClass.setYear(classYear);
        return schoolClass;
    }
}