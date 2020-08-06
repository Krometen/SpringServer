package dh.krometen.GET;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetFile {

    private static final String DIRECTORY = "/home/dess/all/serv/";
    private static final String DEFAULT_FILE_NAME = "read";

    @RequestMapping(value="/GetNamesOfAllFiles", method=RequestMethod.GET)
public @ResponseBody
    String GetNamesOfAllFiles(){
    File folder = new File(DIRECTORY);

    final String[] extension = {""/*No filter by extension*/};
    String[] files = folder.list((folder1, name) -> {
                for(String ignored : extension)
                    return true;
                return false;
            }
    );
    return Arrays.toString(files);
}

    @RequestMapping(value="/download", method=RequestMethod.GET)
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