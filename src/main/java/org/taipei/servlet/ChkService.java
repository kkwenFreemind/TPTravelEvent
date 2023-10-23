package org.taipei.servlet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ChkService {
    @RequestMapping(value = "/chkservice", method = RequestMethod.POST)
    public ResponseEntity<?> checkservice(@RequestBody String request) throws Exception {

        return ResponseEntity.ok(new String("ok"));

    }


}
