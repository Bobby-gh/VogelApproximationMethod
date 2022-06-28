import java.util.ArrayList;
import java.util.List;

import routing_system.GraphA;

public class App {

    public static void main(String[] args) {
        System.out.println("Connecting vertices...");

        ArrayList<Object> vertices = new ArrayList<>();
        vertices.add("Great Hall");
        vertices.add("School of Graduate Studies");
        vertices.add("Volta Hall");
        vertices.add("Balme Library");
        vertices.add("New N Block");
        vertices.add("Department of Political Science");
        vertices.add("School of Engineering");
        vertices.add("Department of Mathematics");
        vertices.add("UGBS");
        vertices.add("UGCS");
        vertices.add("Chemistry Department");
        vertices.add("International Programs Office");
        vertices.add("JQB");
        vertices.add("Department of Geography");
        vertices.add("Legon Hall");
        vertices.add("Akuafo Hall");
        vertices.add("College of Basic and Applied Sciences");
        vertices.add("Mensah Sarbah Hall");
        vertices.add("Athletic Oval");
        vertices.add("GCB");

        GraphHashMap graphHM = new GraphHashMap(vertices);
        graphHM.addEdge("Great Hall", "School of Graduate Studies", 1200);
        graphHM.addEdge("Great Hall", "Volta Hall", 1100);
        graphHM.addEdge("School of Graduate Studies", "Volta Hall", 450);
        graphHM.addEdge("School of Graduate Studies", "UGBS", 350);
        graphHM.addEdge("Volta Hall", "UGBS", 230);
        graphHM.addEdge("Volta Hall", "Legon Hall", 190);
        graphHM.addEdge("UGBS", "UGCS", 188);
        graphHM.addEdge("UGBS", "GCB", 450);
        graphHM.addEdge("GCB", "New N Block", 120);
        graphHM.addEdge("New N Block", "Department of Political Science", 400);
        graphHM.addEdge("Department of Political Science", "Department of Mathematics", 500);
        graphHM.addEdge("Department of Mathematics", "Chemistry Department", 170);
        graphHM.addEdge("UGBS", "Chemistry Department", 350);
        graphHM.addEdge("UGCS", "Legon Hall", 450);
        graphHM.addEdge("Legon Hall", "Mensah Sarbah Hall", 550);
        graphHM.addEdge("Legon Hall", "Athletic Oval", 350);
        graphHM.addEdge("Legon Hall", "Akuafo Hall", 500);
        graphHM.addEdge("Legon Hall", "Balme Library", 450);
        graphHM.addEdge("Balme Library", "Akuafo Hall", 270);
        graphHM.addEdge("Balme Library", "Chemistry Department", 400);
        graphHM.addEdge("Mensah Sarbah Hall", "Athletic Oval", 350);
        graphHM.addEdge("Mensah Sarbah Hall", "Akuafo Hall", 850);
        graphHM.addEdge("Athletic Oval", "Akuafo Hall", 550);
        graphHM.addEdge("College of Basic and Applied Sciences", "Akuafo Hall", 900);
        graphHM.addEdge("College of Basic and Applied Sciences", "Department of Geography", 270);
        graphHM.addEdge("JQB", "Department of Geography", 400);
        graphHM.addEdge("JQB", "International Programs Office", 270);
        graphHM.addEdge("School of Engineering", "International Programs Office", 300);
        graphHM.addEdge("International Programs Office", "Chemistry Department", 450);
        graphHM.addEdge("Chemistry Department", "Akuafo Hall", 450);

        Gui UI = new Gui(vertices);

        // graphHM.printGraph();
        System.out.println("--------------------------------Depth First Traversal--------------------------------");
        System.out.println();
        graphHM.printAllPaths("Department of Mathematics", "Balme Library");

        System.out.println("5 possible routes from " + "Source" + " to " + "Destination");
        for (int i = 0; i < 5; i++) {
            List<String> s = graphHM.allPaths.get(i);
            System.out.println(String.join(" -> ", s));
        }
        System.out.println();

        graphHM.listToMatrix();

        GraphAdjacencyMatrix graphAM = new GraphAdjacencyMatrix(vertices.size());
        graphAM.matrix = graphHM.matrix;

        // graphAM.printGraph();

        VogelApproximationMethod VAM = new VogelApproximationMethod(vertices.size(), graphAM.matrix);
        for (int i = 0; i < vertices.size(); i++) {
            VAM.calculatePenalties();
            VAM.getProvisionalRoute();
        }

        // System.out.println("List of provisional routes: "+ VAM.provisionalRoute);
        VAM.getSolutionCost();
        graphHM.getShortestPath();

    }

}
