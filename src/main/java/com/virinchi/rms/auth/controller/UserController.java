package com.virinchi.rms.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.virinchi.rms.auth.entity.User;
import com.virinchi.rms.auth.repository.UserRepository;
import com.virinchi.rms.controller.core.CRUDController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/users")
public class UserController extends CRUDController<User> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    protected static final String prefix = "/src/main/resources/static";
    protected static final String suffix = "/uploads/profile/temp";
    protected static final String TEMP_IMAGE_PATH = System.getProperty("user.dir") + prefix + suffix;
    protected static String fileName = "";

    public UserController() {
        viewPath = "auth/users";
        uri = "users";
        pageTitle = "User";
        activeMenu = "auth";
    }

    @Transactional
    @Override
    public String save(User model) {
        userRepository.save(model);
        return viewPath + "/users?success";
    }

    @Transactional
    @Override
    public boolean saveJson(User model) {
        if (model.getId() == 0) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        } else {
            if (request.getParameter("change_password") != null) {
                model.setPassword(passwordEncoder.encode(model.getPassword()));
            } else {
                User user = userRepository.findById(model.getId()).get();
                model.setPassword(user.getPassword());
            }
        }
        System.out.println("password:" + model.getPassword());
        userRepository.save(model);
        return model.getId() > 0;
    }

    @PostMapping(value = "/check-username")
    @ResponseBody
    public boolean isUserNameExists(@RequestParam("username") String userName,
            @RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        if (id == 0) {
            return userRepository.countByUserName(userName) > 0;
        }
        return userRepository.countByUserNameAndIdNot(userName, id) > 0;
    }

    @PostMapping(value = "/check-email")
    @ResponseBody
    public boolean isEmailExists(@RequestParam("email") String email,
            @RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        if (id == 0) {
            return userRepository.countByEmail(email) > 0;
        }
        return userRepository.countByEmailAndIdNot(email, id) > 0;
    }

    @PostMapping(value = "/store")
    @Transactional
    public String store(User model, @RequestParam("files") MultipartFile file) throws Exception {
        if (model.getId() == 0) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
            if (!file.isEmpty()) {
                String imagePath = saveFile(model, file, 0);
                model.setProfilePicture(imagePath + "/" + fileName);
            }else{
            model.setProfilePicture("/uploads/profile/profile.png");
            }
        } else {
            String oldPhotoPath = repository.findById(model.getId()).get().getProfilePicture();
            if (request.getParameter("change_password") != null) {
                model.setPassword(passwordEncoder.encode(model.getPassword()));
            } else {
                User user = userRepository.findById(model.getId()).get();
                model.setPassword(user.getPassword());
            }
            if (!file.isEmpty()) {
                String imagePath = saveFile(model, file, model.getId());
                model.setProfilePicture(imagePath + "/" + fileName);
            } else if ((file.isEmpty()) && (!oldPhotoPath.isEmpty())) {
                model.setProfilePicture(oldPhotoPath);
            }
        }
        repository.save(model);
        System.out.println("password:" + model.getPassword());

        return "redirect:/" + uri + "?success";
    }

    public String saveFile(User model, MultipartFile file, Integer id) throws Exception {
        String fileUploadPath = "";
        if (id > 0) {

            String absoluteFileUploadPath = TEMP_IMAGE_PATH.replace("temp", id.toString());
            fileUploadPath = suffix.replace("temp", id.toString());
            dirSetup(absoluteFileUploadPath);
            fileName = generateMD5(file.getOriginalFilename());
            Path filePath = Paths.get(absoluteFileUploadPath, fileName);
            Files.write(filePath, file.getBytes());

        } else {

            dirSetup(TEMP_IMAGE_PATH);
            fileName = generateMD5(file.getOriginalFilename());
            Path tempFilePath = Paths.get(TEMP_IMAGE_PATH, fileName);

            Files.write(tempFilePath, file.getBytes());

            model.setProfilePicture(TEMP_IMAGE_PATH + "/" + fileName);
            repository.save(model);
            Integer entryId = model.getId();
            String newFilePath = TEMP_IMAGE_PATH.replace("temp", entryId.toString());
            dirSetup(newFilePath);

            File sourceFolder = new File(TEMP_IMAGE_PATH);
            if (sourceFolder.exists() && sourceFolder.isDirectory()) {
                Path tempPath = Paths.get(TEMP_IMAGE_PATH);
                Path newPath = Paths.get(newFilePath);
                Files.move(tempPath, newPath, StandardCopyOption.REPLACE_EXISTING);
                sourceFolder.mkdir();
            } else {
                System.out.println("File Not Found!");
            }

            fileUploadPath = suffix.replace("temp", entryId.toString());
        }
        return fileUploadPath;
    }

    public void dirSetup(String fileUploadPath) throws IOException {
        File destinationFolder = new File(fileUploadPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        if (destinationFolder.exists()) {
            FileUtils.cleanDirectory(destinationFolder);
        }
    }
}
