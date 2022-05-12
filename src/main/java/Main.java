import bll.DeliveryService;
import presentation.controllers.Controller;

public class Main {
    public static void main(String[] args) {

        //Set<Client> clients= new HashSet<Client>();
        //clients.add(new Client(1,"anamaria123","qwerty"));
        //clients.add(new Client(2,"raull78","pizza"));
        //Serializer serializer= new Serializer();
        //serializer.serializeClients(clients);
        //FileWriterr.readFromCVSFile("products.csv");
        DeliveryService deliveryService= new DeliveryService();
        //liveryService.importProducts("products.csv");
        Controller controller= new Controller(deliveryService);
    }
}
