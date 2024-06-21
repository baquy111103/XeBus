import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Driver> drivers = new ArrayList<>();
    private static List<Route> routes = new ArrayList<>();
    private static List<AssignmentTable> assignments = new ArrayList<>();

    public static void main(String[] args) {
        while (true){
            System.out.println("Menu:");
            System.out.println("1. Nhập danh sách lái xe mới và in ra");
            System.out.println("2. Nhập danh sách tuyến mới và in ra");
            System.out.println("3. Nhập danh sách phân công cho mỗi lái xe và in ra");
            System.out.println("4. Sắp xếp danh sách phân công");
            System.out.println("5. Lập bảng kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe");
            System.out.println("0. Thoát");
            int choice = Integer.parseInt(scanner.nextLine());
            try{
                switch (choice){
                    case 1:
                        inputDriver();
                        printDriver();
                        break;
                    case 2:
                        inputRouter();
                        printRouter();
                        break;
                    case 3:
                        assignRoutes();
                        printAssignment();
                        break;
                    case 4:
                        sort();
                    default:
                        System.out.print("Lựa chọn không hợp lệ ! ");
                }
            }
            catch (Exception e){
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }
    private static void inputDriver(){
        System.out.print("Nhập số lượng lái xe mới : ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin lái xe thứ " +(i+1)+" : ");
            System.out.print("Họ tên : ");
            String fullname = scanner.nextLine();
            System.out.print("Địa chỉ : ");
            String address = scanner.nextLine();
            System.out.print("Số điện thoại : ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Trình độ (Loại A - F) : ");
            String qualification = scanner.nextLine();
            drivers.add(new Driver(fullname,address,phoneNumber,qualification));
        }
    }

    private static void printDriver(){
        System.out.println("Danh sách lái xe : ");
        for (Driver driver : drivers){
            System.out.println(driver);
        }
    }


    private static void inputRouter(){
        System.out.println("Nhập số lượng tuyến mới : ");
        int n = Integer.parseInt(scanner.nextLine());
        for(int i = 0;i<n;i++){
            System.out.println("Nhập thông tin tuyên số "+(i+1)+" : ");
            System.out.print("Khoảng cách : ");
            double distance = Double.parseDouble(scanner.nextLine());
            System.out.print("Số điểm dừng : ");
            int numberOfStops = Integer.parseInt(scanner.nextLine());
            routes.add(new Route(distance,numberOfStops));
        }
    }

    private static void printRouter(){
        System.out.println("Danh sách các tuyến : ");
        for(Route route : routes){
            System.out.println(route);
        }
    }

    private static void assignRoutes(){
        for(Driver driver : drivers){
            AssignmentTable assignmentTable = new AssignmentTable(driver);
            System.out.println("Nhập số lượng phân công cho lái xe "+driver.getFullname()+" : ");
            int m = Integer.parseInt(scanner.nextLine());
            for(int i=0;i<m;i++){
                System.out.println("Nhập thông tin phân công thứ "+(i+1)+ ":");
                System.out.print("Mã tuyễn : ");
                int routeID = Integer.parseInt(scanner.nextLine());
                Route route = findRouteByID(routeID);
                if(route == null){
                    System.out.print("Không tìm thấy mã tuyến có mã "+routeID);
                    continue;
                }
                System.out.print("Số lượt lái : ");
                int numberOfRounds = Integer.parseInt(scanner.nextLine());
                assignmentTable.assignRoute(route, numberOfRounds);
            }
            assignments.add(assignmentTable);

        }
    }

    private static Route findRouteByID(int routeID) {
        return routes.stream()
                .filter(route -> route.getRouteID() == routeID)
                .findFirst()
                .orElse(null);
    }
    private static void printAssignment(){
        System.out.println("Danh sách phân công : ");
        assignments.forEach(System.out::println);
    }
    private static void sort(){
        System.out.println("Sắp xếp danh sách phân công : ");
        System.out.println("1. Theo họ tên lái xe");
        System.out.println("2. Theo số lượng tuyến đảm nhận trong ngày (giảm dần)");
        int choice = Integer.parseInt(scanner.nextLine());
        if(choice == 1 ){
            assignments = assignments.stream()
                    .sorted(Comparator.comparing(a->a.getDriver().getFullname()))
                    .collect(Collectors.toList());
        }
        else if(choice == 2){
            assignments = assignments.stream()
                    .sorted((a1, a2) -> Integer.compare(a2.getAssignments().size(), a1.getAssignments().size()))
                    .collect(Collectors.toList());
        }else{
            System.out.println("Lựa chọn không hợp lệ !");
            return;
        }
        printAssignment();
    }
    private static void printTotalDistances() {
        System.out.println("Bảng kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe:");
        assignments.forEach(assignment -> {
            System.out.println("Lái xe: " + assignment.getDriver().getFullname() +
                    ", Tổng khoảng cách: " + assignment.calculateTotalDistance());
        });
    }
}
