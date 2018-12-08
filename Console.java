import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws JAXBException {

        final String XML_PATH = "src/Employee.xml";
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Employees emps = (Employees) jaxbUnmarshaller.unmarshal(new File(XML_PATH));

        jaxbContext = JAXBContext.newInstance(Employees.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        boolean run = true;

        while (run) {
            System.out.println("(1) Beolvasás és Listáz");
            System.out.println("(2) Lekérdezés");
            System.out.println("(3) Módosít");
            System.out.println("(4) Új adat");
            System.out.println("(5) Ment \n");
            System.out.println("(0) Kilép");
            System.out.println("Kérem válasszon merre szeretne tovább menni! \n");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();

            switch (a) {
                case 1:
                    for (Employee emp : emps.getEmployees()) {
                        System.out.println("Id: " + emp.getId());
                        System.out.println("Keresztnév: " + emp.getFirstName());
                        System.out.println("Vezetéknév: " + emp.getLastName());
                        System.out.println("Kor: " + emp.getAge() + "\n");
                    }
                    break;
                case 2:
                    System.out.println("Kérem adja meg hanyas id-re szeretne rákeresni, hogy létezik-e! \n");
                    Scanner ac = new Scanner(System.in);
                    int b = ac.nextInt();
                    boolean van = false;
                    for(Employee emp : emps.getEmployees()) {
                        if (emp.getId() == b) {
                            van = true;
                            break;
                        }
                        else {
                            van = false;
                        }
                    }
                    if (van) System.out.println("A keresett id létezik. \n");
                    else System.out.println("A keresett id nem létezik. \n");
                    break;
                case 3:
                    System.out.println("Kérem adja meg hány évesre szeretné átírni az éretékeket.");
                    Scanner ab = new Scanner(System.in);
                    int c = ab.nextInt();
                    for (Employee emp : emps.getEmployees()) {
                        emp.setAge(c);
                    }
                    break;
                case 4:
                    System.out.println("Kérem adja meg az értékeket sorban enterrel elválasztva! (Id, Vezetéknév, Keresztnév, Kor)");
                    Scanner ad = new Scanner(System.in);
                    int d = ad.nextInt();
                    Scanner ae = new Scanner(System.in);
                    String e = ae.nextLine();
                    Scanner af = new Scanner(System.in);
                    String f = ae.nextLine();
                    Scanner ag = new Scanner(System.in);
                    int g = ag.nextInt();

                    Employee emp1 = new Employee();
                    emp1.setId(d);
                    emp1.setFirstName(e);
                    emp1.setLastName(f);
                    emp1.setAge(g);
                    emps.getEmployees().add(emp1);
                    break;
                case 5:
                    jaxbMarshaller.marshal(emps, new File(XML_PATH));
                    System.out.println("A mentés megtörtént! \n");
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Nem megfelelő számot adtál meg!");
                    break;
            }
        }
    }
}
