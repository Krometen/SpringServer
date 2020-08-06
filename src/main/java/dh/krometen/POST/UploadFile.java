package dh.krometen.POST;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import static dh.krometen.ServDir.DIRECTORY;

@Controller
public class UploadFile {

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(
                                DIRECTORY+name)));
                stream.write(bytes);
                stream.close();
                return "You have successfully uploaded the " + name + " in " + DIRECTORY + name;
            } catch (Exception e) {
                return "You failed to load " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to load " + name + " because the file is empty.\n";
        }
    }
}