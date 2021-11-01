package seedu.programmer.model.util;

import java.util.Random;

import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;


/**
 * Contains utility methods for populating {@code ProgrammerError} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Allard Quek"), new StudentId("A0212421H"), new ClassId("B01"),
                    new Email("e0518431@u.nus.edu")),
            new Student(new Name("Samay Sagar"), new StudentId("A0212422H"), new ClassId("B02"),
                    new Email("e0513441@u.nus.edu")),
            new Student(new Name("Erwin Quek"), new StudentId("A0212423H"), new ClassId("B03"),
                    new Email("e0538441@u.nus.edu")),
            new Student(new Name("Tom Jerry"), new StudentId("A0267423H"), new ClassId("B07"),
                    new Email("e0535541@u.nus.edu")),
            new Student(new Name("David Li"), new StudentId("A0212424H"), new ClassId("B04"),
                    new Email("e0518641@u.nus.edu")),
            new Student(new Name("Irfan Ibrahim"), new StudentId("A0212425H"), new ClassId("B05"),
                    new Email("e0558441@u.nus.edu")),
            new Student(new Name("Roy Balakrishnan"), new StudentId("A0212426H"), new ClassId("B06"),
                    new Email("e0518481@u.nus.edu")),
            new Student(new Name("Remington Meiner"), new StudentId("A0214256A"), new ClassId("B01"),
                    new Email("e0518231@u.nus.edu")),
            new Student(new Name("Dennie Povey"), new StudentId("A0214257B"), new ClassId("B02"),
                    new Email("e0518232@u.nus.edu")),
            new Student(new Name("Linette Mitri"), new StudentId("A0214258C"), new ClassId("B03"),
                    new Email("e0518233@u.nus.edu")),
            new Student(new Name("Bea Affron"), new StudentId("A0214259D"), new ClassId("B04"),
                    new Email("e0518234@u.nus.edu")),
            new Student(new Name("Darn Siddons"), new StudentId("A0214260E"), new ClassId("B05"),
                    new Email("e0518235@u.nus.edu")),
            new Student(new Name("Inessa Mellmoth"), new StudentId("A0214261A"), new ClassId("B06"),
                    new Email("e0518236@u.nus.edu")),
            new Student(new Name("Ernst Sproston"), new StudentId("A0214262B"), new ClassId("B07"),
                    new Email("e0518237@u.nus.edu")),
            new Student(new Name("Trude Antwis"), new StudentId("A0214263C"), new ClassId("B08"),
                    new Email("e0518238@u.nus.edu")),
            new Student(new Name("Ammamaria McJarrow"), new StudentId("A0214264D"), new ClassId("B09"),
                    new Email("e0518239@u.nus.edu")),
            new Student(new Name("Ansley Silverlock"), new StudentId("A0214265E"), new ClassId("B10"),
                    new Email("e0518240@u.nus.edu")),
            new Student(new Name("Perkin Tant"), new StudentId("A0214266F"), new ClassId("B01"),
                    new Email("e0518241@u.nus.edu")),
            new Student(new Name("Gavrielle Staner"), new StudentId("A0214267G"), new ClassId("B02"),
                    new Email("e0518242@u.nus.edu")),
            new Student(new Name("Salomo Evreux"), new StudentId("A0214268A"), new ClassId("B03"),
                    new Email("e0518243@u.nus.edu")),
            new Student(new Name("Berny Murie"), new StudentId("A0214269B"), new ClassId("B04"),
                    new Email("e0518244@u.nus.edu")),
            new Student(new Name("Teirtza Fish"), new StudentId("A0214270C"), new ClassId("B05"),
                    new Email("e0518245@u.nus.edu")),
            new Student(new Name("Ronnie Claworth"), new StudentId("A0214271D"), new ClassId("B06"),
                    new Email("e0518246@u.nus.edu")),
            new Student(new Name("Andra Caville"), new StudentId("A0214272E"), new ClassId("B07"),
                    new Email("e0518247@u.nus.edu")),
            new Student(new Name("Kala Worsell"), new StudentId("A0224124F"), new ClassId("B08"),
                    new Email("e0518248@u.nus.edu")),
            new Student(new Name("Walker Hartzenberg"), new StudentId("A0224125G"), new ClassId("B09"),
                    new Email("e0518249@u.nus.edu")),
            new Student(new Name("Nancie Strivens"), new StudentId("A0224126A"), new ClassId("B10"),
                    new Email("e0518250@u.nus.edu")),
            new Student(new Name("Warde Mularkey"), new StudentId("A0224127F"), new ClassId("B01"),
                    new Email("e0518251@u.nus.edu")),
            new Student(new Name("Thorndike Conor"), new StudentId("A0224128A"), new ClassId("B02"),
                    new Email("e0518252@u.nus.edu")),
            new Student(new Name("Cole Jacques"), new StudentId("A0224129E"), new ClassId("B03"),
                    new Email("e0518253@u.nus.edu")),
            new Student(new Name("Ferdinand Westrey"), new StudentId("A0224130T"), new ClassId("B04"),
                    new Email("e0518254@u.nus.edu")),
            new Student(new Name("Sonja Hake"), new StudentId("A0224131G"), new ClassId("B05"),
                    new Email("e0518255@u.nus.edu")),
            new Student(new Name("Meade Thomen"), new StudentId("A0224132D"), new ClassId("B06"),
                    new Email("e0518256@u.nus.edu")),
            new Student(new Name("Nadeen Kliemke"), new StudentId("A0224133E"), new ClassId("B07"),
                    new Email("e0518257@u.nus.edu")),
            new Student(new Name("Judd Chatenet"), new StudentId("A0224134B"), new ClassId("B08"),
                    new Email("e0518258@u.nus.edu")),
            new Student(new Name("Pebrook Chaim"), new StudentId("A0224135A"), new ClassId("B09"),
                    new Email("e0518259@u.nus.edu"))
        };
    }

    public static Lab[] getSampleLab() {
        return new Lab[]{
            new Lab(1, 15),
            new Lab(2, 20),
        };
    }

    public static int getRandomLabScore (Lab lab) {
        Random r = new Random();
        int maxScore = lab.getTotalScore();
        int score = r.nextInt(maxScore);
        return score;
    }

    public static ReadOnlyProgrammerError getSampleProgrammerError() {
        ProgrammerError sampleAb = new ProgrammerError();
        for (Student sampleStudent : getSampleStudents()) {
            Lab[] sampleLabs = getSampleLab();
            for (int i = 0; i < sampleLabs.length; i++) {
                Lab sampleLab = sampleLabs[i];
                if (i == 0) { // only add random scores to 1 labs
                    int randomLabScore = getRandomLabScore(sampleLab);
                    sampleLab.updateActualScore(randomLabScore);
                }
                sampleStudent.addLab(sampleLab);
            }
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Same method as getSampleProgrammerError but with ProgrammerError object as its return type.
     * @return ProgrammerError filled with sample data
     */
    public static ProgrammerError fillSampleProgrammerError() {
        ProgrammerError sampleAb = new ProgrammerError();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

}
