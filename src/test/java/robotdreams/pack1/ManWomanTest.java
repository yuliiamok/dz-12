package robotdreams.pack1;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import robotdreams.automation.utils.CSVFileReader;

import java.util.List;

public class ManWomanTest {

    public static final String PATH_TO_CSV = "./src/test/resources/pack1/personParseData.csv";

    private enum Gender {
        MAN,
        WOMAN
    }

    static public class PersonDataRecord extends CsvToBean {
        @CsvBindByName(column = "gender")
        private Gender gender;
        @CsvBindByName(column = "first_name")
        private String firstName;
        @CsvBindByName(column = "last_name")
        private String lastName;
        @CsvBindByName(column = "age")
        private Integer age;
        @CsvBindByName(column = "expected_is_retired")
        private boolean expectedIsRetired;
    }

    @DataProvider
    public Object[][] parseData() {
        List<PersonDataRecord> list = CSVFileReader.csvToDataProvider(PersonDataRecord.class, PATH_TO_CSV);
        return list.stream().map(obj -> {
            Gender gender = Gender.valueOf(String.valueOf(obj.gender));
            Person person;
            switch (gender) {
                case MAN:
                    person = new Man(obj.firstName.trim(), obj.lastName.trim(), obj.age, null);
                    break;
                case WOMAN:
                    person = new Woman(obj.firstName.trim(), obj.lastName.trim(), obj.age, null);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return new Object[]{person, obj.expectedIsRetired};
        }).toArray(Object[][]::new);
    }

    @Test(dataProvider = "parseData", groups = "pack1")
    public void testParse(Person person, boolean expectedeIsRetired) {
        SoftAssert softAssert = new SoftAssert();

        if (person.getFirstName() != null) {
            softAssert.assertFalse(person.getFirstName().isEmpty());
        }
        if (person.getLastName() != null) {
            softAssert.assertFalse(person.getLastName().isEmpty());
        }
        softAssert.assertEquals(person.isRetired(), expectedeIsRetired);
        softAssert.assertAll();
    }

}
