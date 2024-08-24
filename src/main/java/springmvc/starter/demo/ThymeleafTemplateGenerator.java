package springmvc.starter.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ThymeleafTemplateGenerator {

    private static final String BASE_PATH = "src/main/resources/templates/";

    public static void main(String[] args) {
        try {
            createDirectoriesAndFiles();
            System.out.println("Thymeleaf templates generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectoriesAndFiles() throws IOException {
        createDirectory("fragments");
        createDirectory("layouts");
        createDirectory("pages/home");
        createDirectory("pages/admin");
        createDirectory("pages/user");
        createDirectory("pages/product");

        createFile("fragments/header.html", getHeaderFragmentContent());
        createFile("fragments/footer.html", getFooterFragmentContent());
        createFile("fragments/sidebar.html", getSidebarFragmentContent());
        createFile("layouts/layout.html", getLayoutContent());
        createFile("pages/home/index.html", getHomePageContent());
        createFile("pages/admin/dashboard.html", getAdminDashboardContent());
        createFile("pages/user/profile.html", getUserProfileContent());
        createFile("pages/product/list.html", getProductListContent());
        createFile("pages/product/detail.html", getProductDetailContent());
    }

    private static void createDirectory(String dirName) {
        File directory = new File(BASE_PATH + dirName);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Directory created: " + dirName);
        }
    }

    private static void createFile(String fileName, String content) throws IOException {
        File file = new File(BASE_PATH + fileName);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            }
            System.out.println("File created: " + fileName);
        }
    }

    private static String getHeaderFragmentContent() {
        return "<div th:fragment=\"header\">\n" +
                "    <h1>My Application Header</h1>\n" +
                "    <!-- Navigation or other header content -->\n" +
                "</div>\n";
    }

    private static String getFooterFragmentContent() {
        return "<div th:fragment=\"footer\">\n" +
                "    <p>&copy; 2024 My Application. All rights reserved.</p>\n" +
                "</div>\n";
    }

    private static String getSidebarFragmentContent() {
        return "<div th:fragment=\"sidebar\">\n" +
                "    <ul>\n" +
                "        <li><a th:href=\"@{/home}\">Home</a></li>\n" +
                "        <li><a th:href=\"@{/admin/dashboard}\">Dashboard</a></li>\n" +
                "        <li><a th:href=\"@{/user/profile}\">Profile</a></li>\n" +
                "        <li><a th:href=\"@{/product/list}\">Products</a></li>\n" +
                "    </ul>\n" +
                "</div>\n";
    }

    private static String getLayoutContent() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\">\n" +
                "<head>\n" +
                "    <title>My Application</title>\n" +
                "    <link rel=\"stylesheet\" th:href=\"@{/css/styles.css}\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "    <header th:include=\"fragments/header :: header\"></header>\n" +
                "    <nav th:include=\"fragments/sidebar :: sidebar\"></nav>\n" +
                "    <div layout:fragment=\"content\"></div>\n" +
                "    <footer th:include=\"fragments/footer :: footer\"></footer>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String getHomePageContent() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\">\n" +
                "<body layout:decorate=\"layouts/layout.html\">\n" +
                "    <div layout:fragment=\"content\">\n" +
                "        <h1>Welcome to the Home Page</h1>\n" +
                "        <p>This is the main content of the home page.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String getAdminDashboardContent() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\">\n" +
                "<body layout:decorate=\"layouts/layout.html\">\n" +
                "    <div layout:fragment=\"content\">\n" +
                "        <h1>Admin Dashboard</h1>\n" +
                "        <p>Admin-specific content goes here.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String getUserProfileContent() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\">\n" +
                "<body layout:decorate=\"layouts/layout.html\">\n" +
                "    <div layout:fragment=\"content\">\n" +
                "        <h1>User Profile</h1>\n" +
                "        <p>User profile content goes here.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String getProductListContent() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\">\n" +
                "<body layout:decorate=\"layouts/layout.html\">\n" +
                "    <div layout:fragment=\"content\">\n" +
                "        <h1>Product List</h1>\n" +
                "        <p>List of products will be displayed here.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String getProductDetailContent() {
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\">\n" +
                "<body layout:decorate=\"layouts/layout.html\">\n" +
                "    <div layout:fragment=\"content\">\n" +
                "        <h1>Product Details</h1>\n" +
                "        <p>Details of the selected product will be displayed here.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
