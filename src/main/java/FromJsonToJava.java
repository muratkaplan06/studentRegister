import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
public class FromJsonToJava implements Runnable {
    public static void main(String[] args) {
      Thread t=new Thread(new FromJsonToJava());

      t.start();

    }

    @Override
    public void run() {
        List<Student> studentList = new ArrayList<Student>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/jsonFile.json");
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            /*Json dosyamız birden person objesine karşılık gelecek bilgi tuttuğu için bir JsonArray
            tanımlıyoruz ve bu JsonArray’i parser nesnesinin parse metodu ile oluşturuyoruz*/
            gJsonArray jsonArray = parser.parse(fileReader).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                Student person = gson.fromJson(jsonArray.get(i), Student.class);
                studentList.add(person);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (Student p:studentList) {
            try {
                //Thread kullanarak 3 saniyede bekleyerek ekrana basar
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.getAge()+" "+p.getName()+" "+p.getSurname());
        }
    }
}
