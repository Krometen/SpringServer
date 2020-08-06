package dh.krometen.GET;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static dh.krometen.ServDir.DIRECTORY;

@Controller
public class DownloadFile {

    @RequestMapping(value="/Download", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam(value="fileName", required = false) String fileName) throws IOException {

        System.out.println("fileName: " + fileName);
        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .body(resource);
    }

}