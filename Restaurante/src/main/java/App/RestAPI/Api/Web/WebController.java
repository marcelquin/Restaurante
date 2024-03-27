package App.RestAPI.Api.Web;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class WebController {

    @RequestMapping("/")
    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }




}
