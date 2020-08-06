package dh.krometen.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Arrays;

import static dh.krometen.ServDir.DIRECTORY;

@Controller
public class GetNamesOfAllFiles{
    @RequestMapping(value="/GetNamesOfAllFiles", method= RequestMethod.GET)
    public @ResponseBody
    String getNamesOfAllFiles(){
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

}
