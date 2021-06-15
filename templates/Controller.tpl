package {{packageName}}controller;

import {{packageName}}controller.core.CRUDController;
import {{packageName}}entity.{{Entity}};

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/{{url}}")
public class {{Entity}}Controller extends CRUDController<{{Entity}}> {

    public {{Entity}}Controller() {
        viewPath = "{{entity}}";
        uri = "{{url}}";
        pageTitle = "{{Entity}}";
        activeMenu = "{{entity}}";
    }

}
