// package com.beginsprintboot.restaurant.config;

// import java.nio.file.Paths;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Value("${upload.directory}")
//     private String uploadDirectory;

//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         // Chemin absolu vers votre dossier d'images
//         String absolutePathToImages = Paths.get(uploadDirectory).toFile().getAbsolutePath();
//         String dirName = "springboot-images";
//         if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
//         registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
//     }
// }